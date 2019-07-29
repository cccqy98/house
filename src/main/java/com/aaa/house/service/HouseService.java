package com.aaa.house.service;

import java.util.List;
import java.util.Map;

/**
 * fileName:HouseService
 * description:
 * author:guoxiaoxuan
 * createTime:2019/7/26 21:39
 * versoin:1.0.0
 */
public interface HouseService {
    /**
     * 查询房屋信息
     * @param map
     * @return
     */
    List<Map> queryHouseAll(Map map);

    /**
     * 查询总数
     * @param map
     * @return
     */
    int queryHousePageCount(Map map);
}
