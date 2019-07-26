package com.aaa.house.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Classname：LoginRegDao
 * @author: L_Fly
 * @Date: 2019/7/26  Time：10:09
 * @Version 1.0.0
 */
@Repository
public interface LoginRegDao {

    @Select("select * from user")
    List<Map> query();

    @Select("select * from user where u_phone=#{phone}")
    int queryByPhone(@Param("phone")int phone);

}
