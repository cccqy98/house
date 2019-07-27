package com.aaa.house.controller;

import com.aaa.house.dao.LoginRegDao;
import com.aaa.house.entity.User;
import com.aaa.house.service.LoginRegService;
import com.aaa.house.service.LoginRegServiceImpl;
import com.aaa.house.utils.ISysConstants;
import com.aaa.house.utils.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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

    /**
     * 获取验证码
     *
     * @param phone 手机号
     * @return 交互实体类
     */
    @RequestMapping("/getPhoneCode")
    public ResultUtil test1(@RequestParam("phone") String phone) {
        User result = loginRegService.queryPhone(phone);
        ResultUtil resultUtil = new ResultUtil();
        if (result != null) {
            resultUtil.setCode(ISysConstants.ERRORCODE);
            resultUtil.setMsg("该手机号已经注册");
        } else {
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            resultUtil.setMsg("验证码已发送，请在3分钟内使用");
        }
        return resultUtil;
    }

    /**
     * 注册账号
     *
     * @param user 用户实体
     * @param code 验证码
     * @return 交互工具类
     */
    @RequestMapping("/register")
    public ResultUtil test2(User user, String code) {
        //调用验证接口
        int reg = loginRegService.checkCodeInsertSelective(user, code);
        //交互实体类
        ResultUtil resultUtil = new ResultUtil();
        if (reg == -1) {
            resultUtil.setCode(ISysConstants.OTHERTIPS);
            resultUtil.setMsg("验证码错误");
        } else if (reg == 1) {
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
        } else {
            resultUtil.setCode(ISysConstants.ERRORCODE);
            resultUtil.setMsg("注册失败");
        }
        return resultUtil;
    }

    /**
     * 核对登录
     *
     * @param user
     * @return
     */
    @RequestMapping("/checkLogin")
    public ResultUtil test3(User user) {
        User login = loginRegService.checkLogin(user);
        //交互实体类
        ResultUtil result = new ResultUtil();
        if (login != null) {
            result.setCode(ISysConstants.SUCCESSCODE);
        } else {
            result.setCode(ISysConstants.ERRORCODE);
            result.setMsg("登录失败,手机号或密码错误");
        }
        return result;
    }

    //判断是否登录
    @RequestMapping("/judgeCusLogin")
    public ResultUtil judgeCusLogin() {
        User judgeCusLogin = loginRegService.judgeCusLogin();
        ResultUtil result = new ResultUtil();
        if (judgeCusLogin == null) {
            result.setCode(ISysConstants.ERRORCODE);
        } else {
            result.setCode(ISysConstants.SUCCESSCODE);
            result.setObject(judgeCusLogin.getUPetname());
        }
        return result;
    }


    @RequestMapping("/login")//验证登录
    public ResultUtil login(User user) {
        System.out.println("登录:" + user);
        //身份令牌
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUPhone(), user.getUPassword());
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        ResultUtil result = new ResultUtil();
        return result;
    }
}
