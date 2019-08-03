package com.aaa.house.controller;/**
 * @Author: GZB
 * @Description:
 * @Date:Created in 14:15 2019/8/2
 * @Modified By:
 */

import com.aaa.house.entity.HouseLaIm;
import com.aaa.house.service.AHouseService;
import com.aaa.house.service.DHouseService;
import com.aaa.house.utils.ISysConstants;
import com.aaa.house.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DHouseController
 * @Author 龚志博
 * @Date 2019/8/2 14:15
 * @Version 1.0
 */
@RestController
public class DHouseController {
    @Autowired
    private AHouseService service;
    @Autowired
    private DHouseService service1;
    @RequestMapping("/getHouse1")
    public Object getHouse(@RequestBody Map map){
        Map mapResult = new HashMap();
        mapResult.put("houseList",service.getHouse(map));
        mapResult.put("total",service1.queryPageCount(map));
        return mapResult;
    }

    /**
     * 查询审核状态
     * @return
     */
    @RequestMapping("queryCode2")
    public List<Map> queryCode2(){
        return service1.queryCode2();
    }

    /**
     *
     * @param map
     * @return
     */
    @RequestMapping("upAudit1")
    public ResultUtil upAudit1(@RequestBody Map map){
        int i = service1.upAudit1(map);
        //交互类
        ResultUtil resultUtil1=new ResultUtil();
        if (i>0){
            resultUtil1.setCode(ISysConstants.SUCCESSCODE);
            resultUtil1.setObject(i);
            return resultUtil1;
        }
        resultUtil1.setCode(ISysConstants.OTHERTIPS);
        return resultUtil1;
    }

}
