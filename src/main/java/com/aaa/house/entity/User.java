package com.aaa.house.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Classname：User
 * @author: L_Fly
 * @Date: 2019/7/26  Time：10:12
 * @Version 1.0.0
 */
@Data
@ToString
public class User implements Serializable {
    private Integer id;//id

    private String uPetname;//昵称

    private String uName; //姓名

    private Integer uSex; //性别

    private String uPhone; //手机号

    private String uCard; //身份证号

    private String uPassword; //密码

    private Integer uState; //状态

    private String uPhoto; //头像

    private static final long serialVersionUID = 1L;

    public User() {
    }
}
