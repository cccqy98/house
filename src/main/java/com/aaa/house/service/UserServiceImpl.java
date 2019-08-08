package com.aaa.house.service;

import com.aaa.house.dao.UserMapper;
import com.aaa.house.entity.House;
import com.aaa.house.entity.User;
import com.aaa.house.utils.CusUtil;
import com.aaa.house.utils.ISysConstants;
import com.aaa.house.utils.OtherUtil;
import com.aaa.house.utils.ResultUtil;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.house.utils.OtherUtil.checkIDCard;
import static com.aaa.house.utils.SecurityCodeUtil.sendSms;

/**
 * @Classname：UserServiceImpl
 * @author: L_Fly
 * @Date: 2019/7/29  Time：10:48
 * @Version 1.0.0
 * 用户数据操作
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    //交互前台显示状态类
    ResultUtil resultUtil = new ResultUtil();
    String randomCode;//验证码
    User user;//用户信息

    /**
     * 获取验证码
     *
     * @param phone 参数手机号
     * @return ResultUtil交互实体类
     */
    @Override
    public ResultUtil queryPhone(String phone) {
        //根据手机号查询用户
        User user = userMapper.selectByPrimaryKey(phone);
        //判断用户是否已存在
        if (user != null) {
            resultUtil.setCode(ISysConstants.ERRORCODE);
            resultUtil.setMsg("该手机号已注册");
            return resultUtil;
        }
        //调用工具类OtherUtil中的randomCode获取随机数
        randomCode = OtherUtil.randomCode();
        System.out.println("赋值" + randomCode);
        try {
            //调用阿里短信服务
            SendSmsResponse sendSms = sendSms(phone, randomCode);
            if (sendSms.getMessage().equals("OK")) {
                resultUtil.setCode(ISysConstants.SUCCESSCODE);
                resultUtil.setMsg("验证码已发送，请在2分钟内使用");
                CusUtil.saveCode(phone, randomCode);
            } else {
                resultUtil.setCode(ISysConstants.SUCCESSCODE);
                resultUtil.setMsg("验证码发送失败");
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return resultUtil;
    }

    /**
     * 核查验证码，动态添加用户
     *
     * @param record 实体类
     * @param code   验证码
     * @return ResultUtil交互实体类
     */
    @Override
    public ResultUtil checkCodeInsertSelective(User record, String code) {
        String phone = record.getUPhone();
        String oldCode = CusUtil.getCode(phone);
        //核对验证码
        if (code.equals(oldCode)) {
            //判断id是否为空
            if (OtherUtil.isEmpty(record.getId())) {
                //性别默认为男
                record.setUSex(1);
                //调用工具类进行MD5密码加密
                record.setUPassword(OtherUtil.MD5(record.getUPassword(), record.getUPhone()));
                //调用工具类获取随机用户昵称
                record.setUPetname(OtherUtil.getUname());
                //调用dao方法进行用户录入，并判断录入是否成功
                if (userMapper.insertSelective(record) > 0) {
                    resultUtil.setCode(ISysConstants.SUCCESSCODE);
                    resultUtil.setMsg("注册成功");
                    return resultUtil;
                }
                resultUtil.setCode(ISysConstants.ERRORCODE);
                resultUtil.setMsg("注册失败");
                return resultUtil;
            }
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        resultUtil.setMsg("验证码错误");
        return resultUtil;
    }

    /**
     * 根据手机号查询用户信息,核对信息进行登录
     *
     * @param record 实体对象
     * @return ResultUtil交互实体类
     */
    @Override
    public ResultUtil checkLogin(User record) {
        //获取手机号
        String phone = record.getUPhone();
        //根据手机号查询用户
        user = userMapper.selectByPrimaryKey(phone);
        System.out.println(user);
        //如果用户是否存在
        if (user != null) {
            //对前台传输密码进行MD5加密
            record.setUPassword(OtherUtil.MD5(record.getUPassword(), phone));
            //得到加密后的密码
            String paramPassword = record.getUPassword();
            //得到原始密码
            String password = user.getUPassword();
            if (password.equals(paramPassword)) {
                CusUtil.saveCus(user);
                resultUtil.setCode(ISysConstants.SUCCESSCODE);
                resultUtil.setMsg("登录成功");
                return resultUtil;
            }
            resultUtil.setCode(ISysConstants.ERRORCODE);
            resultUtil.setMsg("登录失败,手机号/密码不匹配");
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.ERRORCODE);
        resultUtil.setMsg("用户不存在");
        return resultUtil;
    }

    /**
     * 判断登录
     *
     * @return ResultUtil交互实体类
     */
    @Override
    public ResultUtil judgeCusLogin() {
        //获取session中的值
        user = CusUtil.getCusFromSession();
        System.out.println(user);
        if (user == null) {
            resultUtil.setCode(ISysConstants.ERRORCODE);
            resultUtil.setObject(user);
        } else {
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            resultUtil.setObject(user);
        }
        return resultUtil;

//        //判断session中是否由user值
//        if (judgeCusLogin == null) {
//            resultUtil.setCode(ISysConstants.ERRORCODE);
//            resultUtil.setObject(judgeCusLogin);
//        } else {
//            resultUtil.setCode(ISysConstants.SUCCESSCODE);
//            resultUtil.setObject(judgeCusLogin);
//        }
//        return resultUtil;
    }

    /**
     * 根据id修改用户信息
     *
     * @param record
     * @return
     */
    @Override
    public ResultUtil updateByPrimaryKeySelective(User record) {
        if (userMapper.updateByPrimaryKeySelective(record) > 0) {
            //修改信息成功之后更新session

            //获取手机号查询信息
            //往sessions中保存
            //更新Session用户信息
            CusUtil.saveCus(userMapper.selectByPrimaryKey(record.getUPhone()));
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            resultUtil.setMsg("更新成功");
            System.out.println("更新成功");
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.ERRORCODE);
        resultUtil.setMsg("更新失败");
        System.out.println("失败");
        return resultUtil;
    }

    /**
     * 修改密码
     *
     * @param map
     * @return
     */
    @Override
    public ResultUtil upPass(Map map) {

//    //判断是否成功
//        if (service.upPass(map)) {
//        resultUtil.setCode(ISysConstants.SUCCESSCODE);
//        resultUtil.setMsg("修改成功");
//        CusUtil.removeCusson();
//    } else {
//        resultUtil.setCode(ISysConstants.ERRORCODE);
//        resultUtil.setMsg("修改失败");
//    }
//        user.setUPhone((String) map.get("uphone"));
////        user.setUPassword((String) map.get("oldPass"));
//        user.setId((Integer) map.get("id"));
//
//        Object obj = this.checkLogin(user);
//        System.out.println("密码修改服务层");
//        System.out.println(obj);
//        System.out.println("查询是否匹配成功");
//        System.out.println();

        Object oldPass = map.get("oldPass");
        System.out.println(oldPass);
        String oldPass1 = OtherUtil.MD5((String) map.get("oldPass"), user.getUPhone());
        System.out.println(oldPass1);
        System.out.println("==================1");
        String password = user.getUPassword();
        System.out.println(password);
        System.out.println("==================1");

        if (oldPass1.equals(password)) {
            System.out.println("加=================密");
            user.setUPassword(OtherUtil.MD5((String) map.get("newPass"), user.getUPhone()));
            resultUtil = this.updateByPrimaryKeySelective(user);
        } else {
            resultUtil.setCode(ISysConstants.ERRORCODE);
            resultUtil.setMsg("原始密码错误");
        }

        return resultUtil;
    }


    /*    *//**
     * 获取验证码
     *
     * @param phone 参数手机号
     * @return String 返回值为null或者不为null
     *//*
    @Override
    public User queryPhone(String phone) {
        //判断是否存在用户
        User user = userMapper.selectByPrimaryKey(phone);
        //若存在返回不为空result
        if (user != null) {
            return user;
        }
        //调用工具类OtherUtil中的randomCode获取随机数
        randomCode = OtherUtil.randomCode();
        System.out.println("赋值" + randomCode);
        try {
            //调用阿里短信服务
            SendSmsResponse sendSms = sendSms(phone, randomCode);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }*/



    /*    *//**
     * 核查验证码，动态添加用户
     *
     * @param record 实体类
     * @param code   验证码
     * @return int
     *//*
    @Override
    public int checkCodeInsertSelective(User record, String code) {
        //核对验证码
        if (code.equals(randomCode)) {
            //判断id是否为空
            if (OtherUtil.isEmpty(record.getId())) {
                //性别默认为男
                record.setUSex(1);
                //调用工具类进行MD5密码加密
                record.setUPassword(OtherUtil.MD5(record.getUPassword(), record.getUPhone()));
                //调用工具类获取随机用户昵称
                record.setUPetname(OtherUtil.getUname());
                //调用dao方法进行用户录入
                userMapper.insertSelective(record);
                return 1;
            } else {
                return 0;
            }
        }
        return -1;
    }*/


    /**
     * 根据手机号查询用户信息,核对信息进行登录
     *
     * @param record 实体对象
     * @return User实体对象
     *//*
    @Override
    public User checkLogin(User record) {
        //获取手机号
        String phone = record.getUPhone();
        //根据手机号查询用户
        user = userMapper.selectByPrimaryKey(phone);
        //如果用户部位null
        if (user != null) {
            //对前台传输密码进行MD5加密
            record.setUPassword(OtherUtil.MD5(record.getUPassword(), phone));
            //得到加密后的密码
            String paramPassword = record.getUPassword();
            //得到原始密码
            String password = user.getUPassword();
            if (password.equals(paramPassword)) {
                CusUtil.saveCus(user);
                return user;
            }
        }
        return null;
    }*/


//
//    /**
//     * 根据id修改用户信息
//     *
//     * @param record
//     * @return
//     */
//    @Override
//    public boolean updateByPrimaryKeySelective(User record) {
//        int result = userMapper.updateByPrimaryKeySelective(record);
//        if (result > 0) {
//            //修改信息成功之后更新session
//            String phone = record.getUPhone();
//            User user = userMapper.selectByPrimaryKey(phone);
//            //往sessions中保存
//            CusUtil.saveCus(user);
//            return true;
//        }
//        return false;
//    }


//    /**
//     * 修改密码
//     *
//     * @param map
//     * @return
//     */
//    @Override
//    public boolean upPass(Map map) {
//        User user = new User();
//        user.setUPhone((String) map.get("uphone"));
//        user.setUPassword((String) map.get("oldPass"));
//        user.setId((Integer) map.get("id"));
//        Object obj = this.checkLogin(user);
//        if (obj != null) {
//            user.setUPassword(OtherUtil.MD5((String) map.get("newPass"), user.getUPhone()));
////            if (updateByPrimaryKeySelective(user)) {
////                return true;
////            }
//            return false;
//        }
//        return false;
//    }

    /**
     * 验证身份证号
     *
     * @param record
     * @return
     */
    @Override
    public boolean checkID(User record) {
        String uCard = record.getUCard();
        String uName = record.getUName();
//  HttpEntity httpEntity = checkIDCard(uCard, uName);
        boolean b = checkIDCard(uCard, uName);
        if (b) {
            record.setUcardState(2);
            this.updateByPrimaryKeySelective(record);
            return true;
        }
        return false;
    }

    /**
     * 取消关注
     *
     * @param map
     * @return
     */
    @Override
    public boolean delHouse(Map map) {
        if (userMapper.delHouse(map) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean insertRefer(Map map) {
        if (userMapper.insertRefer(map) > 0) {
            return true;
        }
        return false;
    }
}
