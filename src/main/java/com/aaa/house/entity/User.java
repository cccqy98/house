package com.aaa.house.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @Classname：User
 * @author: L_Fly
 * @Date: 2019/7/26  Time：10:12
 * @Version 1.0.0
 */
@Data
@ToString
public class User {
    private Integer id;	        //客户表
    private String  u_petname;	//客户网名
    private String  u_name;	    //客户姓名
    private Integer u_sex;	    // 性别
    private Integer u_phone;  	//客户手机号
    private String  u_card;		//身份证
    private String  u_password;	//密码
    private Integer u_state;    //状态
    private String  u_photo;		//客户头像

}
