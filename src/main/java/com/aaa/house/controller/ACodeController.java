package com.aaa.house.controller;

import com.aaa.house.service.ACodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: ACodeController
 * Author:   曹康
 * Date:     2019/7/31 14:34
 * Description: 字典
 */
@RestController
public class ACodeController {

    @Autowired
    private ACodeService aCodeService;

    /**
     * 查询市区
     * @param map
     * @return
     */
    @RequestMapping("getHouseCode")
    public List<Map> getHouseCode(@RequestBody Map map){
        return aCodeService.HouseCode(map);
    }

    /**
     * 查询房屋审核+发布状态
     * @return
     */
    @RequestMapping("getHouseState")
    public Map getHouseState(){
        return aCodeService.getHouseState();
    }

    /**
     * 查询所有用户状态
     * @return
     */
    @RequestMapping("getUserState")
    public List<Map> getUserState(){
        return aCodeService.getUserState();
    }


    /**
     * 查询所有用户性别
     * @return
     */
    @RequestMapping("getUserSet")
    public List<Map> getUserSet(){
        return aCodeService.getUserSet();
    }

    /**
     * 查询所有员工状态
     * @return
     */
    @RequestMapping("getStaffState")
    public List<Map> getStaffState(){
        return aCodeService.getStaffState();
    }


    /**
     * 查询所有角色
     * @return
     */
    @RequestMapping("getRole")
    public List<Map> getRole(){
        return aCodeService.getRole();
    }


    /**
     * 查询单个角色
     * @return
     */
    @RequestMapping("getRoleStaff")
    public List getRoleStaff(@RequestBody Map map){
        return aCodeService.getRoleStaff(map);
    }
}
