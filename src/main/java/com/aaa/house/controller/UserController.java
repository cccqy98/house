package com.aaa.house.controller;

import com.aaa.house.entity.User;
import com.aaa.house.service.HouseService;
import com.aaa.house.service.UserService;
import com.aaa.house.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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
    @Autowired//用户数据操作服务类
    private UserService service;
    @Autowired//文件工具类
    private FtpUtil ftpUtil;
    @Autowired//文件配置
    private FtpConfig ftpConfig;
    @Autowired//spring core io里面提供的资源加载器
    private ResourceLoader resourceLoader;
    @Autowired
    private HouseService houseService;

    //交互前台显示状态类
    ResultUtil resultUtil = new ResultUtil();

    /**
     * 获取验证码
     *
     * @param phone 手机号
     * @return 交互实体类
     */
    @RequestMapping("/getPhoneCode")
    public ResultUtil code(@RequestParam("phone") String phone) {
//        //查询用户是否已注册
//        User result = service.queryPhone(phone);
//        //判断用户是否注册
//        if (result != null) {
//            resultUtil.setCode(ISysConstants.ERRORCODE);
//            resultUtil.setMsg("该手机号已经注册");
//        } else {
//            resultUtil.setCode(ISysConstants.SUCCESSCODE);
//            resultUtil.setMsg("验证码已发送，请在3分钟内使用");
//        }
//        return resultUtil;
        return service.queryPhone(phone);
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
//        //调用验证接口
//        int reg = service.checkCodeInsertSelective(user, code);
//        //判断录入是否成功，并且给交互类赋值
//        if (reg == -1) {
//            resultUtil.setCode(ISysConstants.OTHERTIPS);
//            resultUtil.setMsg("验证码错误");
//        } else if (reg == 1) {
//            resultUtil.setCode(ISysConstants.SUCCESSCODE);
//        } else {
//            resultUtil.setCode(ISysConstants.ERRORCODE);
//            resultUtil.setMsg("注册失败");
//        }
        return service.checkCodeInsertSelective(user, code);
    }

    /**
     * 核对登录
     *
     * @param user
     * @return
     */
    @RequestMapping("/checkLogin")
    public ResultUtil test3(User user) {
//        User login = service.checkLogin(user);
//        //判断登录查询是否成功
//        if (login != null) {
//            resultUtil.setCode(ISysConstants.SUCCESSCODE);
//        } else {
//            resultUtil.setCode(ISysConstants.ERRORCODE);
//            resultUtil.setMsg("登录失败,手机号或密码错误");
//        }
        return service.checkLogin(user);
    }

    /**
     * 判断是否登录
     *
     * @return
     */
    @RequestMapping("/judgeCusLogin")
    public ResultUtil judgeCusLogin() {
//        User judgeCusLogin = service.judgeCusLogin();
//        //判断session中是否由user值
//        if (judgeCusLogin == null) {
//            resultUtil.setCode(ISysConstants.ERRORCODE);
//            resultUtil.setObject(judgeCusLogin);
//        } else {
//            resultUtil.setCode(ISysConstants.SUCCESSCODE);
//            resultUtil.setObject(judgeCusLogin);
//        }
//        return resultUtil;
        return service.judgeCusLogin();
    }

    /**
     * 退出登录,清空session
     *
     * @return
     */
    @RequestMapping("/outUser")
    public ResultUtil outUser() {
        //清空session
        CusUtil.removeCusson();
        ResultUtil result = new ResultUtil(ISysConstants.SUCCESSCODE, null, null);
        return result;
    }

    /**
     * 文件上传
     *
     * @param headPic
     * @return
     */
    @RequestMapping("/uploadHeadPic")
    public Object uploadHeadPic(@RequestParam MultipartFile headPic) {
        String originalFilename = headPic.getOriginalFilename();
        String newFileName = ftpUtil.upLoad(headPic);
        Map map = new HashMap();
        map.put("originalFilename", originalFilename);
        map.put("newFileName", newFileName);
        return map;
    }

    /**
     * 显示图片
     *
     * @param fileName
     * @return
     */
    @RequestMapping("/show")
    public ResponseEntity show(String fileName) {
        //ftp://admin:admin@192.168.11.116/ftp-dir-xn/84c7f5ed-17e1-4f9a-a775-552254d66386.jpg
        return ResponseEntity.ok(resourceLoader.getResource("ftp://" + ftpConfig.getFtpUserName() + ":" +
                ftpConfig.getFtpPassWord() + "@" + ftpConfig.getRemoteIp() + ftpConfig.getRemotePath() + "/" + fileName));
    }


    /**
     * 用户信息修改
     *
     * @return
     */
    @RequestMapping("update")
    public ResultUtil update(@RequestBody User user) {
//        System.out.println("修改信息");
//        //判断信息是否修改成功
//        if (service.updateByPrimaryKeySelective(user)) {
//            resultUtil.setCode(ISysConstants.SUCCESSCODE);
//            resultUtil.setMsg("更新信息成功");
//            return resultUtil;
//        }
//        resultUtil.setCode(ISysConstants.ERRORCODE);
//        resultUtil.setMsg("更新信息失败");
//        return resultUtil;

        return service.updateByPrimaryKeySelective(user);
    }

    /**
     * 用户身份验证
     *
     * @return
     */
    @RequestMapping("checkID")
    public ResultUtil checkID(@RequestBody User user) {
        //判断ID验证是否成功
        if (service.checkID(user)) {
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            resultUtil.setMsg("实名成功");
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.ERRORCODE);
        resultUtil.setMsg("证号或姓名不匹配");
        return resultUtil;
    }

    /**
     * 用户密码修改
     *
     * @return
     */
    @RequestMapping("upPass")
    public ResultUtil upPass(@RequestBody Map map) {
//        //判断是否成功
//        if (service.upPass(map)) {
//            resultUtil.setCode(ISysConstants.SUCCESSCODE);
//            resultUtil.setMsg("修改成功");
//            CusUtil.removeCusson();
//        } else {
//            resultUtil.setCode(ISysConstants.ERRORCODE);
//            resultUtil.setMsg("修改失败");
//        }
        return service.upPass(map);
    }

    /**
     * 我的房源
     *
     * @param map
     * @return
     */
    @RequestMapping("myHouse")
    public Object myHouse(@RequestBody Map map) {
        Map mapResult = new HashMap();
        mapResult.put("house", houseService.queryHouseAll(map));
        mapResult.put("total", houseService.queryHousePageCount(map));
        return mapResult;
    }

    /**
     * 取消关注
     *
     * @param map
     * @return
     */
    @RequestMapping("delHouse")
    public ResultUtil delHouse(@RequestBody Map map) {
        if (service.delHouse(map)) {
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            resultUtil.setMsg("已取消关注");
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.ERRORCODE);
        resultUtil.setMsg("取消失败404");
        return resultUtil;
    }

    /**
     * 房屋报修
     *
     * @param map
     * @return
     */
    @RequestMapping("refer")
    public Object refer(@RequestBody Map map) {
        System.out.println("报修控制");
        System.out.println(map);
        if (service.insertRefer(map)) {
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            resultUtil.setMsg("报修成功");
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.ERRORCODE);
        resultUtil.setMsg("报修失败");
        return resultUtil;
    }
}