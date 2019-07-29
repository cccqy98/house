package com.aaa.house.service;

import com.aaa.house.dao.HouseDao;
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
    public List<Map> queryHouseAll(Map map) {
        return houseDao.queryHouseAll(map);
    }

    @Override
    public int queryHousePageCount(Map map) {
        return houseDao.queryHousePageCount(map);
    }
}
