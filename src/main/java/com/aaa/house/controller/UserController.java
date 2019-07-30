package com.aaa.house.controller;

import com.aaa.house.entity.User;
import com.aaa.house.service.UserService;
import com.aaa.house.utils.CusUtil;
import com.aaa.house.utils.FtpUtil;
import com.aaa.house.utils.ISysConstants;
import com.aaa.house.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Classname：Login
 * @author: L_Fly
 * @Date: 2019/7/26  Time：9:57
 * @Version 1.0.0
 * @Description: 用户数据控制器
 */
@RestController
@RequestMapping("/loginReg")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private FtpUtil ftpUtil;
    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * 获取验证码
     * @param phone 手机号
     * @return 交互实体类
     */
    @RequestMapping("/getPhoneCode")
    public ResultUtil test1(@RequestParam("phone") String phone) {
        User result = service.queryPhone(phone);
        ResultUtil resultUtil = new ResultUtil();
        if (result != null) {
            resultUtil.setCode(ISysConstants.ERRORCODE);
            resultUtil.setMsg("该手机号已经注册");
            System.out.println(resultUtil.getCode());
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
        int reg = service.checkCodeInsertSelective(user, code);
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
        User login = service.checkLogin(user);
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

    /**
     * 判断是否登录
     *
     * @return
     */
    @RequestMapping("/judgeCusLogin")
    public ResultUtil judgeCusLogin() {
        User judgeCusLogin = service.judgeCusLogin();
        ResultUtil result = new ResultUtil();
        if (judgeCusLogin == null) {
            result.setCode(ISysConstants.ERRORCODE);
        } else {
            result.setCode(ISysConstants.SUCCESSCODE);
            judgeCusLogin.setUPassword("********");
            judgeCusLogin.setUPhone("123456789");
            result.setObject(judgeCusLogin);
        }
        return result;
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping("/outUser")
    public ResultUtil outUser() {
        CusUtil.removeCusson();
        return new ResultUtil(ISysConstants.SUCCESSCODE, null, null);
    }

    /**
     * 显示图片
     *
     * @param fileName
     * @return
     */
    public ResponseEntity show(String fileName) {
        //ftp://username:password@192.168.14.140
        return ResponseEntity.ok(resourceLoader.getResource("ftp://"));
    }
}