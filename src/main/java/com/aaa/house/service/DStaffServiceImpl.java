package com.aaa.house.service;/**
 * @Author: GZB
 * @Description:
 * @Date:Created in 16:40 2019/8/7
 * @Modified By:
 */

import com.aaa.house.dao.DStaffMapping;
import com.aaa.house.entity.Staff;
import com.aaa.house.utils.CusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName DStaffServiceImpl
 * @Author 龚志博
 * @Date 2019/8/7 16:40
 * @Version 1.0
 */
@Service
public class DStaffServiceImpl implements DStaffService{
    @Autowired
    private  DStaffMapping mapping;
    @Override
    public List<Staff> queryStaffId() {
        return mapping.queryStaffId(CusUtil.getStaff().getStaff_id());
    }

    @Override
    public int updateStaff(Staff staff) {
        staff.setStaff_id(CusUtil.getStaff().getStaff_id());
        return mapping.updateStaff(staff);
    }
}
