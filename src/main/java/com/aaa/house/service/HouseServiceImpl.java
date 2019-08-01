package com.aaa.house.service;

import com.aaa.house.dao.HouseDao;
import com.aaa.house.entity.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * fileName:HouseServiceImpl
 * description:
 * author:guoxiaoxuan
 * createTime:2019/7/26 22:10
 * versoin:1.0.0
 */
@Service
public class HouseServiceImpl implements HouseService{

    @Autowired
    private HouseDao houseDao;

    @Override
    public List<House> queryHouseAll(Map map) {
        List<House> list = houseDao.queryHouseAll(map);
        for (House ho : list) {
            Integer id=ho.getId();
            List<String> strings = houseDao.selectLable(id);
            ho.setHouseLabel(strings);
        }
        return list;
    }

    @Override
    public Integer queryHousePageCount(Map map) {
        Integer i=houseDao.queryHousePageCount(map);
        System.out.println(i);
        return i;
    }

    @Override
    public List<Map> selectLayout() {
        return houseDao.selectLayout();
    }
}
