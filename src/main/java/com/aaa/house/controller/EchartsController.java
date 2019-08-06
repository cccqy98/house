package com.aaa.house.controller;/**
 * @Author: GZB
 * @Description:
 * @Date:Created in 16:43 2019/8/6
 * @Modified By:
 */

import com.aaa.house.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName EchartsController
 * @Author 龚志博
 * @Date 2019/8/6 16:43
 * @Version 1.0
 */
@RestController
public class EchartsController {
    @Autowired
    private EchartsService service;
    @RequestMapping("queryDEcharts")
    public List<Map> queryDEcharts(@RequestParam Map mapParam){
        return service.queryDPact(mapParam);
    }
}
