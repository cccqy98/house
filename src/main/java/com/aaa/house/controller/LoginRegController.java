package com.aaa.house.controller;

import com.aaa.house.dao.LoginRegDao;
import com.aaa.house.entity.User;
import com.aaa.house.service.LoginRegService;
import com.aaa.house.utils.ISysConstants;
import com.aaa.house.utils.ResultUtil;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.util.Map;

/**
 * @Classname：Login
 * @author: L_Fly
 * @Date: 2019/7/26  Time：9:57
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/loginReg")
public class LoginRegController {

    @Autowired
    private LoginRegService loginRegService;


    @RequestMapping("/getPhoneCode")
    public ResultUtil test1(@RequestParam("phone") String phone){
        System.out.println("验证码");
        String result=loginRegService.queryPhone(phone);
        ResultUtil resultUtil=new ResultUtil();
        if (result!=null){
            resultUtil.setCode(ISysConstants.ERRORCODE);
            resultUtil.setMsg("该手机号已经注册");
        }else{
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            resultUtil.setMsg("验证码已发送，请在5分钟内使用");
        }
        return resultUtil;
    }

    @RequestMapping("register")
    public ResultUtil test2(@RequestBody User user,String code){
        System.out.println("===========================注册=================================");
        System.out.println(user);
        int reg=loginRegService.insertSelective(user,code);
        ResultUtil resultUtil=new ResultUtil();
        if (reg==-1){
            resultUtil.setCode(ISysConstants.OTHERTIPS);
            resultUtil.setMsg("验证码错误");
        }else if(reg==1){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
        }else {
            resultUtil.setCode(ISysConstants.ERRORCODE);
            resultUtil.setMsg("注册失败");
        }
        return resultUtil;
    }


//    @RequestMapping("/registerCus")
//    public Result registerCus(Customer customer,String code){
//        int reg=service.registerCus(customer,code);
//        Result result=new Result();
//        if (reg==-1){
//            result.setCode(ISysConstants.OTHERTIPS);
//            result.setMsg("验证码错误");
//        }else if(reg==1){
//            result.setCode(ISysConstants.SUCCESSCODE);
//        }else {
//            result.setCode(ISysConstants.ERRORCODE);
//            result.setMsg("注册失败");
//        }
//        return result;
//    }


}
