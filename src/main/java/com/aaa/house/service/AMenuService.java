package com.aaa.house.service;

import com.aaa.house.entity.Tree;
import com.aaa.house.utils.ResultUtil;

import java.util.List;
import java.util.Map;

/**
 * 权限
 */
public interface AMenuService {
    /**
     *  查询权限 树形菜单
     * @return
     */
    ResultUtil getPowers();

    /**
     * 获取所有的权限
     * @return
     */
    ResultUtil getPowersAll();


    /**
     * 添加权限
     * @return
     */
    ResultUtil inPowers(Map map);

    /**
     * 修改权限删除权限
     * @param map
     * @return
     */
    ResultUtil upPowers(Map map);

    /**
     * 删除权限
     * @param id
     * @return
     */
    ResultUtil deletePowers(int id);
}
