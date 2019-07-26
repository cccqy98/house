package com.aaa.house.dao;

import com.aaa.house.entity.Staff;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * 员工 正删改查
 * 曹康
 */
public interface AStaffMappng {

    //员工登录
    @Select("select * from staff where staff_num=#{staff_num} and staff_state=1")
    Staff selectStaff(Staff staff);

    //员工添加
    @Insert("INSERT INTO staff(staff_num,staff_name,staff_phone,staff_sex,staff_password,staff_portrait)\n" +
            "VALUES(staff_num=#{staff_num},staff_name=#{staff_name},staff_phone=#{staff_phone},staff_sex=#{staff_sex},staff_password=#{staff_password},staff_portrait=#{staff_portrait})")
    int insertStaff(Staff staff);
}
