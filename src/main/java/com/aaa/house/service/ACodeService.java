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
}
