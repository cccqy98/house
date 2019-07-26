package com.aaa.house.service;

import com.aaa.house.dao.AStaffMappng;
import com.aaa.house.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * FileName: AStaffServiceImpl
 * Author:   曹康
 * Date:     2019/7/26 10:16
 * Description: 员工
 */
@Service
public class AStaffServiceImpl implements AStaffService{
    @Autowired
    private AStaffMappng aStaffMappng;

    //员工登录
    @Override
    public Staff selectStaff(Staff staff) {
        return aStaffMappng.selectStaff(staff);
    }
    //查询所有员工
    @Override
    public List<Staff> selectStaffAll() {
        return aStaffMappng.selectStaffAll();
    }
    //员工添加
    @Override
    public int insertStaff(Staff staff) {
        System.out.println(staff);
        return aStaffMappng.insertStaff(staff);
    }
}
