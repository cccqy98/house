package com.aaa.house.service;

import com.aaa.house.dao.ACodeMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * FileName: ACodeServiceImpl
 * Author:   曹康
 * Date:     2019/7/31 14:32
 * Description: 字典表
 */
@Service
public class ACodeServiceImpl implements ACodeService {

    @Autowired
    private ACodeMapping aCodeMapping;

    /**
     * 查询市
     * @param map
     * @return
     */
    @Override
    public List<Map> HouseCode(Map map) {
        return aCodeMapping.getHouseCode(map);
    }

    /**
     * 房屋状态
     * @return
     */
    @Override
    public List<Map> getHouseState() {
        return aCodeMapping.getHouseState();
    }

    /**
     * 审核状态
     * @return
     */
    @Override
    public List<Map> getHouseAudit() {
        return aCodeMapping.getHouseAudit();
    }

    /**
     * 查询用户状态
     * @return
     */
    @Override
    public List<Map> getUserState() {
        return aCodeMapping.getUserState();
    }

    /**
     * 性别
     * @return
     */
    @Override
    public List<Map> getUserSet() {
        return aCodeMapping.getUserSet();
    }

    /**
     * 员工状态
     * @return
     */
    @Override
    public List<Map> getStaffState() {
        return aCodeMapping.getStaffState();
    }

    /**
     * 全部角色
     * @return
     */
    @Override
    public List<Map> getRole() {
        return aCodeMapping.getRole();
    }

    /**
     * 获取单个用户角色
     * @param map
     * @return
     */
    @Override
    public List getRoleStaff(Map map) {
        List<Map> aa=aCodeMapping.getRoleStaff(map);
        List list=new ArrayList();
        for (Map map1 : aa) {
            list.add(map1.get("rid"));
        }
        return list;
    }
}
