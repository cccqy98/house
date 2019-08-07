package com.aaa.house.service;

import com.aaa.house.entity.RolePerm;
import com.aaa.house.utils.ResultUtil;

import java.util.List;
import java.util.Map;

/**
 * 角色
 */
public interface ARoleService {


    /**
     * 获取所有的角色
     * @return
     */
    ResultUtil getRoleAll();


    /**
     * 添加权限
     * @return
     */
    ResultUtil inRole(Map map);

    /**
     * 修改权限删除权限
     * @param map
     * @return
     */
    ResultUtil upRole(Map map);

    /**
     * 删除权限
     * @param role_id
     * @return
     */
    ResultUtil deleteRole(int role_id);




    /**
     * 根据角色ID查询以前该角色关联的所有权限
     * @param rid
     * @return
     */
    List<Integer> getPowersByRoleId(int rid);

}
