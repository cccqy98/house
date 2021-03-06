package com.aaa.house.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * FileName: RolePerm
 * Author:   曹康
 * Date:     2019/8/7 9:14
 * Description: 角色权限中间表
 */
@Data
public class RolePerm implements Serializable {
    private String pid;
    private String rid;
}
