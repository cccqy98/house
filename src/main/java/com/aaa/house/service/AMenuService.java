package com.aaa.house.service;

import com.aaa.house.entity.Tree;

import java.util.List;

/**
 * 权限
 */
public interface AMenuService {
    /**
     *  查询权限 树形菜单
     * @return
     */
    List<Tree> getPowers();
}
