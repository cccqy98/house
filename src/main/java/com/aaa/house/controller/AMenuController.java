package com.aaa.house.controller;

import com.aaa.house.service.AMenuService;
import com.aaa.house.utils.ISysConstants;
import com.aaa.house.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 权限菜单
     * @return
     */
    @RequestMapping("getTreeAll")
    public ResultUtil getTreeAll(){
        Object object=aMenuService.getPowers();
        //调用交互类
        ResultUtil resultUtil=new ResultUtil();
        //设置状态码
        resultUtil.setCode(ISysConstants.SUCCESSCODE);
        //数据
        resultUtil.setObject(object);
        return resultUtil;
    }
}
