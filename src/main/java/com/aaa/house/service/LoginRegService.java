package com.aaa.house.service;

import com.aaa.house.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Classname：LoginRegService
 * @author: L_Fly
 * @Date: 2019/7/26  Time：11:21
 * @Version 1.0.0
 */

public interface LoginRegService {
    /**
     * 根据手机号查询是否有此用户
     * @param phone 参数手机号
     * @return int
     */
    User queryPhone(String phone);

    /**
     * 核查验证码，动态添加用户
     * @param record 实体类
     * @param code 验证码
     * @return int
     */
    int   checkCodeInsertSelective(User record,String code);

    /**
     * 根据手机号查询用户信息
     * @param record 实体对象
     * @return User实体对象
     */
    User checkLogin(User record);


    User selectByPrimaryKey(String phone);


    User judgeCusLogin();
}
