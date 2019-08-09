package com.aaa.house.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * FileName: Role
 * Author:   曹康
 * Date:     2019/8/5 16:57
 * Description: 角色
 */
@Data
public class Role implements Serializable {
    private String role_id;
    private String role_name;
    private String role_describe;
}
