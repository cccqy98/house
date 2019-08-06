package com.aaa.house.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * FileName: Staff
 * Author:   曹康
 * Date:     2019/7/26 9:50
 * Description: 员工实体类
 */
@Data
public class Staff implements Serializable {
    private int staff_id;
    private String staff_num;//员工编号
    private String staff_name;//员工名字
    private String staff_phone;//电话
    private int staff_sex;//性别
    private String staff_password;//密码
    private String staff_card;//身份证号
    private int staff_state;//状态
    private String staff_portrait;//路径

    private String staffsex;//汉字性别
    private String staffstate;//汉字状态


    //角色
    private List<Role> roles;


    public Staff() {
    }


}
