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
public class TreeRole implements Serializable {
    private Integer role_id;
    private String label;//权限名
    private Integer roleid;//父Id
    private String role_name;//父名字
    private List<TreeRole> children;//子
    private String role_describe;//描述
    private String per_staffName;//添加人编号

    public TreeRole() {
    }
}
