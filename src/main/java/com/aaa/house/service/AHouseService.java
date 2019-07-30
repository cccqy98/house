package com.aaa.house.service;

import com.aaa.house.entity.HouseUser;

import java.util.List;
import java.util.Map;

/**
 * 房屋
 */
public interface AHouseService {
    /**
     * 查询所有未联系的房
     * @return
     */
    List<HouseUser> getHouseUserAll(Map map);

    /**
     * 查询所有未联系的房 全部
     * @param map
     * @return
     */
    int getHouseUserquall(Map map);
    /**
     * 经济人联系房源
     * @return
     */
    int upHouseUser(Map map);
}
