package com.aaa.house.controller;

import com.aaa.house.entity.Maintain;
import com.aaa.house.service.DMaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.function.ObjIntConsumer;

/**
 * @ClassName DMaintionController
 * @Author 龚志博
 * @Date 2019/7/28 13:23
 * @Version 1.0
 */
@RestController
public class DMaintainController {
    @Autowired
    private DMaintainService service;

    /**
     * 查询全部报修单
     * @return
     */
    @RequestMapping("/queryAll")
    public Object queryAll(){
        return  service.queryAll();
    }

    /**
     * 根据报修状态查询
     * @param map
     * @return
     */
    @RequestMapping("/queryMa")
    public Object queryStatic(@RequestBody  Map map){
        return service.queryStatic(map);
    }

    /**
     * 添加
     * @param maintain
     * @return
     */
    @RequestMapping("/add")
    public Object AddMaintain(Maintain maintain){
        return service.AddMaintain(maintain);
    }
}
