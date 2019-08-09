package com.aaa.house.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * FileName: StaffRole
 * Author:   曹康
 * Date:     2019/8/6 9:51
 * Description: 员工角色中间表
 */
@Data
public class StaffRole implements Serializable {
    private String sid;
    private String rid;
}
