package com.aaa.house.service;

import com.aaa.house.dao.BCodeDao;
import com.aaa.house.entity.BHouseUser;
import com.aaa.house.entity.Code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BHouseAdd {

    @Autowired
    private BCodeDao dao;

    //code字段集合
    public List<Code> showAddress(String type) {
        return dao.showAddress(type);
    }

    //code ID
    public Code showAddres(String id) {
        return dao.showAddres(id);
    }

    //添加到house表
    public int addUser(BHouseUser user) {
        return dao.addUser(user);
    }
}
