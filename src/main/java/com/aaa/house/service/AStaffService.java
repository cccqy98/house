package com.aaa.house.service;

import com.aaa.house.entity.Staff;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface AStaffService {

    //员工登录
    Staff selectStaff(Staff staff);

    //判断员工是否登录
    Staff selectSession();

    //查询所有员工
    List<Staff> selectStaffAll();

    //员工添加
    int insertStaff(Staff staff);
}
