package com.aaa.house.service;

import java.util.List;
import java.util.Map;

/**
 * 字典
 */
public interface ACodeService {
    /**
     * 查询所有街道
     * @return
     */
    List<Map> HouseCode(Map map);


    /**
     * 查询所有房屋状态
     * @return
     */
    List<Map> getHouseState();

    /**
     * 查询审核状态
     * @return
     */
    List<Map> getHouseAudit();


    /**
     * 查询员工状态
     * @return
     */
    List<Map> getUserState();

    /**
     * 查询员工性别
     * @return
     */
    List<Map>getUserSet();

    /**
     * 查询员工状态
     * @return
     */
    List<Map> getStaffState();

    /**
     * 查询所有角色
     * @return
     */
    List<Map> getRole();

    /**
     * 查询单个用户角色
     * @return
     */
    List getRoleStaff(Map map);
}
