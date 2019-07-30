package com.aaa.house.dao;

import com.aaa.house.entity.Tree;
import com.aaa.house.utils.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    @Select("select perm.id,perm.per_name as label,perm.per_id,perm.per_url,perm.per_icon from \n" +
            "permission perm,role_perm rp,role,staff,staff_role sr\n" +
            "where staff.staff_id=sr.sid and sr.rid=role.role_id \n" +
            "and rp.rid=role.role_id and rp.id=perm.id and staff.staff_id=#{staff_id}")
    List<Tree> getPowers(Integer staff_id);
}
