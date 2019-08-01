package com.aaa.house.service;

import com.aaa.house.dao.CityDao;
import com.aaa.house.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * fileName:CityServiceImpl
 * description:
 * author:guoxiaoxuan
 * createTime:2019/7/31 14:57
 * versoin:1.0.0
 */
@Service
public class CityServiceImpl implements CityService{

    @Autowired
    private CityDao cityDao;

    @Override
    public List<City> selectCity() {
        List<City> mapList = cityDao.selectCity();
        for (City city : mapList) {
            Integer cNumber = city.getCodeNumber();
            if (cNumber!=null){
                List<City> cities = cityDao.selectStreet(cNumber);
                city.setHouseStreet(cities);
            }

        }
        return mapList;
    }
}
