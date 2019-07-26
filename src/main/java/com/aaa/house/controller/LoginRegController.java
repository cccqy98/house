package com.aaa.house.controller;

import com.aaa.house.dao.LoginRegDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;

/**
 * @Classname：Login
 * @author: L_Fly
 * @Date: 2019/7/26  Time：9:57
 * @Version 1.0.0
 */
@RestController
public class LoginRegController {

    @Autowired
    private LoginRegDao loginRegDao;

//    测试可用
    @RequestMapping("test")
    public Object query(){
        System.out.println("测试");
        return loginRegDao.query();
    }



    @RequestMapping("/getUsableGetPhone")
    public Integer getUsableGetPhone(@RequestParam("phone")Integer phone){
//        Customer customer1 = service.getUsableGetPhone(customer);
//        Result result=new Result();
//        if (customer1!=null){
//            result.setCode(ISysConstants.ERRORCODE);
//            result.setMsg("该手机号已经注册");
//        }else{
//            result.setCode(ISysConstants.SUCCESSCODE);
//            result.setMsg("验证码已发送，请在5分钟内使用");
//        }
        return null;
    }



}
