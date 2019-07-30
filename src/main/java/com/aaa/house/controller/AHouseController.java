package com.aaa.house.controller;

import com.aaa.house.entity.HouseUser;
import com.aaa.house.service.AHouseService;
import com.aaa.house.utils.ISysConstants;
import com.aaa.house.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: AHouseController
 * Author:   曹康
 * Date:     2019/7/29 22:17
 * Description: 房屋
 */
@RestController
public class AHouseController {

    @Autowired
    private AHouseService aHouseService;

    /**
     * 获取所有未联系的房源 加模糊查询
     * @return
     */
    @RequestMapping("getHouseUser")
    public Object getHouseUser(@RequestBody Map map){
        Map map1=new HashMap();
        map1.put("personlist",aHouseService.getHouseUserAll(map));
        map1.put("total",aHouseService.getHouseUserquall(map));
        return map1;
    }

    /**
     * 经济人联系房源
     * @return
     */
    @RequestMapping("upHouseUser")
    public ResultUtil upHouseUser(@RequestBody Map map){
        System.out.println("sdfsdfdsfds"+map);
        int i = aHouseService.upHouseUser(map);
        //交互类
        ResultUtil resultUtil=new ResultUtil();
        if (i>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            resultUtil.setObject(i);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }

}
