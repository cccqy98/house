package com.aaa.house.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * FileName: Tree
 * Author:   曹康
 * Date:     2019/7/28 15:02
 * Description: 树形菜单
 */
@Data
public class Tree implements Serializable {
    private Integer id;
    private String label;//权限名
    private Integer per_id;//父Id
    private List<Tree> children;//子
    private String per_url;
    private String per_icon;

    public Tree() {
    }
}
