package com.aaa.house.service;

import com.aaa.house.dao.UserMapper;
import com.aaa.house.entity.User;
import com.aaa.house.utils.CusUtil;
import com.aaa.house.utils.OtherUtil;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.aaa.house.utils.SecurityCodeUtil.sendSms;

/**
 * @Classname：UserServiceImpl
 * @author: L_Fly
 * @Date: 2019/7/29  Time：10:48
 * @Version 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {
    String randomCode;//验证码
    User user;//用户信息

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取验证码
     * @param phone 参数手机号
     * @return String 返回值为null或者不为null
     */
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
    }

    /**
     * 核查验证码，动态添加用户
     *
     * @param record 实体类
     * @param code   验证码
     * @return int
     */
    @Override
    public int checkCodeInsertSelective(User record, String code) {
        System.out.println("判断code" + randomCode);
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
    }

    /**
     * 根据手机号查询用户信息,核对信息进行登录
     * @param record 实体对象
     * @return User实体对象
     */
    @Override
    public User checkLogin(User record) {
        //获取手机号
        String phone = record.getUPhone();
        //根据手机号查询用户
        User user = userMapper.selectByPrimaryKey(phone);
        //如果用户部位null
        if (user!=null){
            record.setUPassword(OtherUtil.MD5(record.getUPassword(), phone));
            String paramPassword=record.getUPassword();
            System.out.println("参数密码"+paramPassword);
            String password=user.getUPassword();
            System.out.println("查询到的密码"+password);
            if (password.equals(paramPassword)){
                CusUtil.saveCus(user);
                return user;
            }
        }
        return null;
    }

    /**
     * 判断登录
     *
     * @return
     */
    @Override
    public User judgeCusLogin() {
        user=CusUtil.getCusFromSession();
        if(user==null){
            return null;
        }
        return user;
    }
}
