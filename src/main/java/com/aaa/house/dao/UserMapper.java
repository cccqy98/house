package com.aaa.house.dao;

import com.aaa.house.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Classname：UserMapper
 * @author: L_Fly
 * @Date: 2019/7/26  Time：10:09
 * @Version 1.0.0
 * @Description: 用户数据访问层
 */
@Repository
public interface UserMapper {
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

    /**
     * 根据id修改用户信息
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 取消关注房源
     *
     * @param map
     * @return
     */
    @Delete("DELETE FROM house_attention WHERE ah_uid=#{userId} and ah_hid=#{houseId};")
    int delHouse(Map map);

    @Insert("INSERT INTO maintain (ma_user,ma_cause,ma_house) VALUES (#{name},#{cause},#{houseId});")
    int insertRefer(Map map);


    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int updateByPrimaryKey(User record);

}
