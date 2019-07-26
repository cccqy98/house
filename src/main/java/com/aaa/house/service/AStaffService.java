package com.aaa.house.service;

import com.aaa.house.entity.Staff;


public interface AStaffService {

    //员工登录
    Staff selectStaff(Staff staff);

    //员工添加
    int insertStaff(Staff staff);
}
