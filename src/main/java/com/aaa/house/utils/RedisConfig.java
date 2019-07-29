package com.aaa.house.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

/**
 * FileName: RedisConfig
 * Author:   曹康
 * Date:     2019/7/29 19:10
 * Description: 二级缓存 redis
 */

@Configuration
@PropertySource("classpath:config.properties")
@ConfigurationProperties(prefix = "redis")
public class  RedisConfig{
    
    //最大能够保持空闲状态的链接数
    private int maxIdle;
     //最大连接数
    private int maxTotal;
   //最大的等待时长
    private int maxWaitMillis;
   //当调用borrow Object方法时，是否进行有效性检查
    private boolean testOnBorrow;
    //集群的节点字符串
    private String nodes;

    /**
     * 初始化redis连接池
     * @return
     */
    @Bean("jedisPoolConfig")
    public JedisPoolConfig getJedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        return jedisPoolConfig;
    }
    /**
     * 初始化集群配置
     * @return
     */
    @Bean("redisClusterConfiguration")
    public RedisClusterConfiguration getRedisClusterConfiguration(){
        RedisClusterConfiguration redisClusterConfiguration =new RedisClusterConfiguration();
        //redis.nodes=192.168.23.30:7001,192.168.23.30:7002,192.168.23.30:7003,192.168.23.31:7004,192.168.23.31:7005,192.168.23.31:7006
      //分割字符串实例化 RedisNode
        String[] clusterNodes = nodes.split(",");
        for (String clusterNode : clusterNodes) {
            String[] cnode = clusterNode.split(":");
            RedisNode node = new RedisNode(cnode[0],Integer.valueOf(cnode[1]));
            redisClusterConfiguration.addClusterNode(node);
        }
        return redisClusterConfiguration;
    }
    /**
     * 初始化jedisConnectionFactory
     * @return
     */
    @Bean("jedisConnectionFactory")
    public JedisConnectionFactory getJedisConnectionFactory(){
       // JedisConnectionFactory(RedisClusterConfiguration clusterConfig, JedisPoolConfig poolConfig)
         return new JedisConnectionFactory(getRedisClusterConfiguration(),getJedisPoolConfig());
    }
    /**
     * RedisCache 注入JedisConnectionFactory
     * @return
     */
    @Bean("redisCache")
    public Object bindJedisConnectionFactoryToredisCache(){
        //绑定当前实例化的工厂类到RedisCache中
        RedisCache.setJedisConnectionFactory(getJedisConnectionFactory());
        return null;
    }



    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }
}
