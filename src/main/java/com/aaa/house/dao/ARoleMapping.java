package com.aaa.house.dao;

import com.aaa.house.entity.RolePerm;
import com.aaa.house.entity.Tree;
import com.aaa.house.entity.TreeRole;
import com.aaa.house.utils.RedisCache;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * FileName: AMenuMapping
 * Author:   曹康
 * Date:     2019/7/28 16:04
 * Description: 角色
 */
@Repository
//开启二级缓存 reids
@CacheNamespace(implementation = RedisCache.class)
public interface ARoleMapping {

    /**
     * 获取所有的角色
     * @return
     */
    @Select("select role_id,role_name as label,roleid,role_describe from role")
    List<TreeRole> getRoleAll();


    /**
     * 添加角色
     * @return
     */
    int inRole(Map map);

    /**
     * 修改角色
     * @param map
     * @return
     */
    @Update("update role set role_name=#{label},roleid =#{roleid},role_describe=#{role_describe} where role_id=#{role_id}")
    int upRole(Map map);


    /**
     * 删除角色
     * @param role_id
     * @return
     */
    @Delete("delete from role where role_id=#{role_id}")
    int deleteRole(int role_id);






    /**
     * 向关联表添加数据
     * @return
     */
    @Insert("<script> insert into role_perm(rid,pid) values\n" +
            "\n" +
            "    <foreach collection=\"list\" item=\"item\" separator=\",\">\n" +
            "        (#{item.rid},#{item.pid})\n" +
            "    </foreach></script>")
    int setRolePerm(List<RolePerm> rolePerms);

    /**
     * 根据角色ID删除以前该角色关联的所有权限
     * @param rid
     * @return
     */
    @Delete("delete from role_perm where rid=#{rid}")
    int deletePemRole(int rid);

    /**
     * 根据角色ID查询以前该角色关联的所有权限
     * @param rid
     * @return
     */
    @Select("select pid from role_perm where rid=#{rid}")
    List<Integer> getPowersByRoleId(int rid);


}
