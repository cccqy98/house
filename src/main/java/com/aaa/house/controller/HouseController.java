package com.aaa.house.controller;

import com.aaa.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * fileName:HouseController
 * description:
 * author:guoxiaoxuan
 * createTime:2019/7/27 10:31
 * versoin:1.0.0
 */
@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private HouseService houseService;

    /**
     * 查询房屋信息
     * @param map
     * @return
     */
    @RequestMapping("/page")
    public Object queryAll(@RequestBody Map map) {
        //验证

        Map mapResult=new HashMap();
        //判断
        mapResult.put("empList", houseService.queryHouseAll(map));
        mapResult.put("total", houseService.queryHousePageCount(map));
        return mapResult;
    }

    /**
     * 查询房间布局
     * @return
     */
    @RequestMapping("/layout")
    public Object selectLayout() {
        return houseService.selectLayout();
    }
}
