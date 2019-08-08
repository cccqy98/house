package com.aaa.house.controller;

import com.aaa.house.service.BulletinService;
import com.aaa.house.utils.ISysConstants;
import com.aaa.house.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * fileName:BulletinController
 * description:
 * author:guoxiaoxuan
 * createTime:2019/8/7 16:40
 * versoin:1.0.0
 */
@RestController
@RequestMapping("/bulletin")
public class BulletinController {

    @Autowired
    private BulletinService bulletinService;

    /**
     * 查询公告栏信息
     * @param map
     * @return
     */
    @RequestMapping("/selectBulletin")
    public Object selectBulletin(@RequestBody Map map) {
        Map mapbulletin=new HashMap();
        mapbulletin.put("empList",bulletinService.selectBulletin(map));
        mapbulletin.put("total", bulletinService.selectBulletinCount(map));
        return mapbulletin;
    }

    /**
     * 添加公告
     * @param map
     * @return
     */
    @RequestMapping("/insertBulletin")
    public ResultUtil insertBulletin(@RequestBody Map map){
        Integer b = bulletinService.insertBulletin(map);
        ResultUtil resultUtil=new ResultUtil();
        if (b>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            resultUtil.setMsg("公告添加成功");
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        resultUtil.setMsg("公告添加失败");
        return resultUtil;
    }
    /**
     * 删除公告
     * @param bid
     * @return
     */
    @RequestMapping("/deleteBullById/{bid}")
    public ResultUtil deleteBullById(@PathVariable Integer bid){
        Integer d = bulletinService.deleteBullById(bid);
        ResultUtil resultUtil=new ResultUtil();
        if (d>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            resultUtil.setMsg("公告删除成功");
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        resultUtil.setMsg("公告删除失败");
        return resultUtil;
    }
}
