package com.aaa.house.dao;

import com.aaa.house.entity.City;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * fileName:CityDao
 * description:
 * author:guoxiaoxuan
 * createTime:2019/7/31 14:56
 * versoin:1.0.0
 */
@Repository
public interface CityDao {
    /**
     * 获取城市
     * @return
     */
    List<City> selectCity();

    /**
     * 获取街道
     * @return
     */
    List<City> selectStreet(Integer cNumber);
}
