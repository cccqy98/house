package com.aaa.house.utils;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * FileName: RedisCache
 * Author:   曹康
 * Date:     2019/7/29 19:19
 * Description: 二级缓存 redis
 */
public class RedisCache implements Cache {


    private static JedisConnectionFactory jedisConnectionFactory;

    private String id;

    /**
     *
     * @param jedisConnectionFactory
     */
    public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory){
        RedisCache.jedisConnectionFactory =jedisConnectionFactory;
    }
    /**
     *构造缓存对象
     * @param id
     */
    public RedisCache(final String id) {
        if(null==id){
           throw new IllegalArgumentException("缓存类实例化必须依赖一个id.....");
        }
        this.id = id;
    }

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object val) {
        RedisConnection connection = null;
        try {
             connection = jedisConnectionFactory.getConnection();
            //实例化序列化对象
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            connection.set(serializer.serialize(key),serializer.serialize(val));
        } catch (SerializationException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null)
                connection.close();
        }
    }

    @Override
    public Object getObject(Object key) {
        RedisConnection connection = null;
        try {
             connection = jedisConnectionFactory.getConnection();
            //实例化序列化对象
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            //获取值的byte数组
            byte[] bytesKey = connection.get(serializer.serialize(key));
            Object deserialize = serializer.deserialize(bytesKey);
            return deserialize;
        } catch (SerializationException e) {
            e.printStackTrace();
        } finally {
            if(connection!=null)
                connection.close();
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        RedisConnection connection = null;
        try {
            connection = jedisConnectionFactory.getConnection();
            //实例化序列化对象
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            return connection.expireAt(serializer.serialize(key),0);
        } catch (SerializationException e) {
            e.printStackTrace();
        } finally {
            if(connection!=null)
                connection.close();
        }
        return null;
    }

    @Override
    public void clear() {
        RedisConnection connection = null;
        try {
            connection = jedisConnectionFactory.getConnection();
            connection.flushDb();
            connection.flushAll();
        } catch (SerializationException e) {
            e.printStackTrace();
        } finally {
            if(connection!=null)
                connection.close();
        }
    }

    @Override
    public int getSize() {
        RedisConnection connection = null;
        try {
            connection = jedisConnectionFactory.getConnection();
            return Integer.valueOf(connection.dbSize().toString());
        } catch (SerializationException e) {
            e.printStackTrace();
        } finally {
            if(connection!=null)
                connection.close();
        }
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }
}
