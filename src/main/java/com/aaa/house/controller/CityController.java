package com.aaa.house.controller;

import com.aaa.house.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * fileName:CityController
 * description:
 * author:guoxiaoxuan
 * createTime:2019/7/31 15:49
 * versoin:1.0.0
 */
@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;

    /**
     * 查询城市街道
     * @return
     */
    @RequestMapping("/region")
    public Object selectCity() {
        return cityService.selectCity();
    }
}
