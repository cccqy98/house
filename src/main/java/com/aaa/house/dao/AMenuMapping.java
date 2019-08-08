package com.aaa.house.dao;

import com.aaa.house.entity.Tree;
import com.aaa.house.utils.RedisCache;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * FileName: AMenuMapping
 * Author:   曹康
 * Date:     2019/7/28 16:04
 * Description: 菜单权限
 */
@Repository
/*//开启二级缓存 reids
@CacheNamespace(implementation = RedisCache.class)*/
public interface AMenuMapping {
    /**
     * 查询权限 树形菜单
     * @param staff_id
     * @return
     */
    @Select("select perm.id,perm.per_name as label,perm.per_id,perm.per_url,perm.per_icon \n" +
            "FROM staff s\n" +
            "join staff_role sro\n" +
            "on s.staff_id=sro.sid\n" +
            "join role ro\n" +
            "on sro.rid=ro.role_id\n" +
            "join role_perm rp\n" +
            "on ro.role_id=rp.rid\n" +
            "join permission perm\n" +
            "on rp.pid=perm.id\n" +
            "where s.staff_id=#{staff_id}")
    List<Tree> getPowers(Integer staff_id);


    /**
     * 获取所有的权限
     * @return
     */
    @Select("select id,per_name as label,per_id,per_url,per_icon from permission")
    List<Tree> getPowersAll();


    /**
     * 添加权限
     * @return
     */
    @Insert("INSERT into permission(per_name,per_id,per_url,per_staffName)\n" +
            "VALUES(#{label},#{per_id},#{per_url},#{per_staffName})")
    int inPowers(Map map);

    /**
     * 修改权限
     * @param map
     * @return
     */
    @Update("update permission set per_name=#{label},per_id =#{per_id},per_url=#{per_url} where id=#{id}")
    int upPowers(Map map);


    /**
     * 删除权限
     * @param id
     * @return
     */
    @Delete("delete from permission where id=#{id}")
    int deletePowers(int id);

}
