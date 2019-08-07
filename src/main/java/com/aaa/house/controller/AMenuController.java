package com.aaa.house.controller;

import com.aaa.house.service.AMenuService;
import com.aaa.house.utils.ISysConstants;
import com.aaa.house.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * FileName: AMenuController
 * Author:   曹康
 * Date:     2019/7/28 20:28
 * Description: 权限
 */
@RestController
public class AMenuController {
    @Autowired
    private AMenuService aMenuService;

    /**
     * 个人权限菜单
     * @return
     */
    @RequestMapping("getTreeAll")
    public ResultUtil getPowers(){
        return aMenuService.getPowers();
    }
    /**
     * 全部权限菜单
     * @return
     */
    @RequestMapping("upTreeAll")
    public ResultUtil getTreeAll(){
        return aMenuService.getPowersAll();
    }

    /**
     * 添加权限
     * @param map
     * @return
     */
    @RequestMapping("inPowers")
    public ResultUtil inPowers(@RequestBody Map map) {
        return aMenuService.inPowers(map);
    }

    /**
     * 修改权限
     * @param map
     * @return
     */
    @RequestMapping("upPowers")
    public ResultUtil upPowers(@RequestBody Map map) {
        return aMenuService.upPowers(map);
    }

    /**
     * 删除权限
     * @param id
     * @return
     */
    @RequestMapping("deletePowers")
    public ResultUtil deletePowers(int id) {
        return aMenuService.deletePowers(id);
    }

}
