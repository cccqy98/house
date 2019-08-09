package com.aaa.house.service;/**
 * @Author: GZB
 * @Description:
 * @Date:Created in 16:40 2019/8/7
 * @Modified By:
 */

import com.aaa.house.dao.DStaffMapping;
import com.aaa.house.entity.Staff;
import com.aaa.house.utils.CusUtil;
import com.aaa.house.utils.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public int updateStaff(Map map) {
        //获取  加密
        String password=map.get("staff_password").toString();
        String name=map.get("staff_num").toString();
        String staff_password= PasswordHelper.passwordStaff(name,password);
        map.put("staff_password",staff_password);
        int a=mapping.updateStaff(map);//修改
        Staff staff = mapping.selectStaff(map);
        CusUtil.setStaff(staff);//重新存
        return a;
    }


}
