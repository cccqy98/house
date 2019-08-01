package com.aaa.house.controller;

import com.aaa.house.service.ACodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * FileName: ACodeController
 * Author:   曹康
 * Date:     2019/7/31 14:34
 * Description: 字典
 */
@RestController
public class ACodeController {

    @Autowired
    private ACodeService aCodeService;

    /**
     * 查询市区
     * @param map
     * @return
     */
    @RequestMapping("getHouseCode")
    public List<Map> getHouseCode(@RequestBody Map map){
        return aCodeService.HouseCode(map);
    }
}
