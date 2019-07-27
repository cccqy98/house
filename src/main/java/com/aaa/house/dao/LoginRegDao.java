package com.aaa.house.dao;

import com.aaa.house.entity.User;
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
//    /**
//     * 根据手机号查询是否有此用户
//     * @param phone 参数手机号
//     * @return string
//     */
//    @Select("select id from user where u_phone=#{phone}")
//    String queryPhone(@Param("phone")String phone);
    /**
     * 动态添加用户
     * @param record
     * @return int
     */
    int insertSelective(User record);

    /**
     * 根据手机号获取用户信息
     * @param phone 手机号
     * @return User实体对象
     */
    User selectByPrimaryKey(String phone);



    int deleteByPrimaryKey(Integer id);

    int insert(User record);





    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
