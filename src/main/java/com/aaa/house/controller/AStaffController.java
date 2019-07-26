package com.aaa.house.controller;

import com.aaa.house.entity.Staff;
import com.aaa.house.service.AStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName: AStaffController
 * Author:   曹康
 * Date:     2019/7/26 10:19
 * Description: 员工
 */
@RestController
public class AStaffController {
    @Autowired
    private AStaffService aStaffService;

    /**
     * 员工登录
     * @param staff 员工实体类
     * @return
     */
    @RequestMapping("/staffid")
    public Object staffId(Staff staff){
        return aStaffService.selectStaff(staff);
    }

    /**
     * 员工添加
     * @param staff
     * @return
     */
    @RequestMapping("/insertStaff")
    public int insertStaff(Staff staff){
        return aStaffService.insertStaff(staff);
    }
}