package com.aaa.house.dao;

import com.aaa.house.entity.House;


import java.util.List;
import java.util.Map;

/**
 * fileName:HouseDao
 * description:
 * author:guoxiaoxuan
 * createTime:2019/7/26 21:47
 * versoin:1.0.0
 */

public interface HouseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(House record);

    int insertSelective(House record);

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

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);
}
