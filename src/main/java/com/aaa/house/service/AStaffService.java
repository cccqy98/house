package com.aaa.house.service;

import com.aaa.house.entity.Staff;
import com.aaa.house.utils.ResultUtil;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


public interface AStaffService {

    //员工登录
    Staff selectStaff(Staff staff);

    //判断员工是否登录
    Staff selectSession();

    //查询所有员工
    List<Staff> selectStaffAll();

    //员工添加
    int insertStaff(Staff staff);

    /**
     * 查询所有用户
     * @return
     */
    Map getUser(Map map);

    /**
     * 修改用户信息
     * @return
     */
    ResultUtil upUser(Map map);
}
