package com.aaa.house.service;

import com.aaa.house.entity.City;

import java.util.List;

/**
 * fileName:CityService
 * description:
 * author:guoxiaoxuan
 * createTime:2019/7/31 14:57
 * versoin:1.0.0
 */
public interface CityService {
    /**
     * 获取城市
     * @return
     */
    List<City> selectCity();

}
