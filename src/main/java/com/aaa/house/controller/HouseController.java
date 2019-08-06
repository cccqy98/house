package com.aaa.house.controller;

import com.aaa.house.entity.User;
import com.aaa.house.service.HouseService;
import com.aaa.house.utils.CusUtil;
import com.aaa.house.utils.ISysConstants;
import com.aaa.house.utils.ResultUtil;
import jdk.nashorn.internal.runtime.Undefined;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
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
        Map mapResult=new HashMap();
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

    /**
     * 根据id查询房屋信息
     * @param map
     * @return
     */
    @RequestMapping("/detail")
    public Map<String,Object> housedetail(@RequestBody Map map) {
        return houseService.housedetail(map);
    }

    /**
     * 关注房源
     * @param map
     * @return
     */
    @RequestMapping("/toFollow")
    public ResultUtil toFollow(@RequestBody Map map){
        Integer i = houseService.insertAtteition(map);
        ResultUtil resultUtil=new ResultUtil();
        if (i>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            resultUtil.setMsg("成功关注房源");
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        resultUtil.setMsg("关注房源失败");
        return resultUtil;
    }
    /**
     * 判断是否关注房源
     * @param map
     * @return
     */
    @RequestMapping("/hasFollow")
    public ResultUtil hasFollow(@RequestBody Map map){
        List<Map> m = houseService.selectAtteition(map);
        ResultUtil resultUtil=new ResultUtil();
        if (m.size()>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }

    /**
     * 关注房源
     * @param map
     * @return
     */
    @RequestMapping("/yuyue")
    public ResultUtil yuyue(@RequestBody Map map){
        Integer l = houseService.insertLookHouse(map);
        ResultUtil resultUtil=new ResultUtil();
        if (l>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            resultUtil.setMsg("预约成功");
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        resultUtil.setMsg("预约失败");
        return resultUtil;
    }
}
