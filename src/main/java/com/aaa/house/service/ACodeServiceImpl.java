package com.aaa.house.service;

import com.aaa.house.dao.ACodeMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * FileName: ACodeServiceImpl
 * Author:   曹康
 * Date:     2019/7/31 14:32
 * Description: 字典表
 */
@Service
public class ACodeServiceImpl implements ACodeService {

    @Autowired
    private ACodeMapping aCodeMapping;

    /**
     * 查询市
     * @param map
     * @return
     */
    @Override
    public List<Map> HouseCode(Map map) {
        return aCodeMapping.getHouseCode(map);
    }
}
