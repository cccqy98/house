package com.aaa.house.service;

import com.aaa.house.entity.House;

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
    List<House> queryHouseAll(Map map);

    /**
     * 查询总数
     * @param map
     * @return
     */
    Integer queryHousePageCount(Map map);
    /**
     * 查询房屋布局
     * @return
     */
    List<Map> selectLayout();
    /**
     * 根据id查询房屋信息
     * @param map
     * @return
     */
    Map<String,Object> housedetail(Map map);
    /**
     * 关注房源
     */
    Integer insertAtteition(Map map);
    /**
     * 判断是否关注房源
     * @param map
     * @return
     */
    List<Map> selectAtteition(Map map);

    /**
     * 预约看房
     */
    Integer insertLookHouse(Map map);
}
