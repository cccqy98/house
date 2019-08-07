package com.aaa.house.service;

import com.aaa.house.dao.UserMapper;
import com.aaa.house.entity.House;
import com.aaa.house.entity.User;
import com.aaa.house.utils.CusUtil;
import com.aaa.house.utils.OtherUtil;
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
    String randomCode;//验证码
    User user;//用户信息

    @Autowired
    private UserMapper userMapper;


    /**
     * 获取验证码
     *
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
     *
     * @param record 实体对象
     * @return User实体对象
     */
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
    }

    /**
     * 判断登录
     *
     * @return
     */
    @Override
    public User judgeCusLogin() {
        //获取session中的值
        user = CusUtil.getCusFromSession();
        if (user == null) {
            return null;
        }
        return user;
    }

    /**
     * 根据id修改用户信息
     *
     * @param record
     * @return
     */
    @Override
    public boolean updateByPrimaryKeySelective(User record) {
        int result = userMapper.updateByPrimaryKeySelective(record);
        if (result > 0) {
            //修改信息成功之后更新session
            String phone = record.getUPhone();
            User user = userMapper.selectByPrimaryKey(phone);
            //往sessions中保存
            CusUtil.saveCus(user);
            return true;
        }
        return false;
    }

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
     * 修改密码
     *
     * @param map
     * @return
     */
    @Override
    public boolean upPass(Map map) {
        User user = new User();
        user.setUPhone((String) map.get("uphone"));
        user.setUPassword((String) map.get("oldPass"));
        user.setId((Integer) map.get("id"));
        Object obj = this.checkLogin(user);
        if (obj != null) {
            user.setUPassword(OtherUtil.MD5((String) map.get("newPass"), user.getUPhone()));
            if (updateByPrimaryKeySelective(user)) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * 取消关注
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
