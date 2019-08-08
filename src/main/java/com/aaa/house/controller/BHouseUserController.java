package com.aaa.house.controller;

import com.aaa.house.service.BHouseAdd;
import com.aaa.house.entity.BHouseUser;
import com.aaa.house.entity.Code;
import com.aaa.house.service.BHouseAdd;
import com.aaa.house.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 控制层
 */
@RestController
public class BHouseUserController {

    @Autowired
    private BHouseAdd add;
/*

    *//**
     * 跳转首页
     * @return
     *//*
    @RequestMapping("/toIndex")
    public String toIndex(){
        return "front/houseResource";
    }


    *//**
     * 第一次加载城市地址
     * @param type
     * @return
     *//*
    @RequestMapping("/showAddress")
    @ResponseBody
    public List<Code> showAddress(String type){

        return add.showAddress(type);
    }

    *//**
     * 查询街道
     * @param id
     * @return
     *//*
    @RequestMapping( "/showAddres")
    @ResponseBody
    public Code showAddres(String id){
        return add.showAddres(id);
    }


//    @RequestMapping("/showNumber")
//    @ResponseBody
//    public Code showAddres(Integer cnumber){
//        return add.showAddres(String.valueOf(cnumber));
//    }

    *//**
     * 执行插入
     * @param user
     * @return
     *//*
    @RequestMapping( "/addUser")
    @ResponseBody
    public String addUser(@RequestBody BHouseUser user) {
        try {
            System.out.println(user);
            //获取市区id
            int uh_urban = user.getUh_urban();
            //获取街道id
            int uh_street = user.getUh_street();
            //获取选取市区code
            Code urban_code = add.showAddres(String.valueOf(uh_urban));
            //获取选取街道code
            Code street_code = add.showAddres(String.valueOf(uh_street));
            //把城市number放入user里
            if(urban_code != null){
                user.setUh_urban(urban_code.getCode_number());
            }
            //把街道number放入user里
            if(street_code != null){
                user.setUh_street(street_code.getCode_number());
            }
            add.addUser(user);
            return "yes";
        } catch (Exception e) {
            // TODO: handle exception
            return "no";
        }

    }*/





 /*   *//**
     * 跳转首页
     *
     * @return
     *//*
    @RequestMapping("/toIndex")
    public String toIndex() {
        return "front/houseResource";
    }*/

  /*  *//**
     * 第一次加载城市地址
     *
     * @param type
     * @return
     *//*
    @RequestMapping("/showAddress")
    @ResponseBody
    public List<Code> showAddress(String type) {

        return add.showAddress(type);
    }*/

   /* *//**
     * 查询街道
     *
     * @param id
     * @return
     *//*
    @RequestMapping("/showAddres")
    @ResponseBody
    public Code showAddres(String id) {
        return add.showAddres(id);
    }*/


//    @RequestMapping("/showNumber")
//    @ResponseBody
//    public Code showAddres(Integer cnumber){
//        return add.showAddres(String.valueOf(cnumber));
//    }

    /**
     * 执行插入
     * @param map
     * @return
     */
    @RequestMapping("/addUser")
    public ResultUtil addUser(@RequestBody Map map) {
        System.out.println("sfdsdfsafasa"+map);
            return add.addUser(map);
        }


}
