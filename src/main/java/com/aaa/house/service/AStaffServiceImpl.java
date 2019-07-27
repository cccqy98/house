package com.aaa.house.service;

import com.aaa.house.dao.AStaffMappng;
import com.aaa.house.entity.Staff;
import com.aaa.house.utils.CusUtil;
import com.aaa.house.utils.PasswordHelper;
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

    public Staff staffAll=null;

    //员工登录
    @Override
    public Staff selectStaff(Staff staff) {
        PasswordHelper.encryptPassword(staff);
         staffAll=aStaffMappng.selectStaff(staff);
        if (staffAll!=null){
            //存session
            CusUtil.setStaff(staffAll);
            return staffAll;
        }
        return null;
    }

    /**
     * 判断员工是否登录
     * @return
     */
    @Override
    public Staff selectSession() {
        //调用工具类CusUtill
         staffAll=CusUtil.getStaff();
         if (staffAll==null){
             return null;
         }
        return staffAll;
    }

    //查询所有员工
    @Override
    public List<Staff> selectStaffAll() {
        return aStaffMappng.selectStaffAll();
    }

    //员工添加
    @Override
    public int insertStaff(Staff staff) {
        return aStaffMappng.insertStaff(staff);
    }
}
