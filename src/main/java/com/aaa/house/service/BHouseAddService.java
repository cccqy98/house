package com.aaa.house.service;

import com.aaa.house.utils.ResultUtil;
import com.mysql.jdbc.util.ResultSetUtil;

import java.util.Map;

/**
 * 客户发布房源
 */
public interface BHouseAddService {
    ResultUtil  addUser(Map map);
}
