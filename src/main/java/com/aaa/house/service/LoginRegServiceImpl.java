package com.aaa.house.service;

import com.aaa.house.dao.LoginRegDao;
import com.aaa.house.entity.User;
import com.aaa.house.utils.OtherUtil;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.aaa.house.utils.SecurityCodeUtil.sendSms;

/**
 * @Classname：LoginRegServiceImpl
 * @author: L_Fly
 * @Date: 2019/7/26  Time：11:21
 * @Version 1.0.0
 */
@Service
public class LoginRegServiceImpl implements LoginRegService {
    String randomCode;//验证码

    @Autowired
    private LoginRegDao loginRegDao;

    @Override
    public String queryPhone(String phone) {
        //判断是否存在用户

        String result = loginRegDao.queryPhone(phone);
        System.out.println("服务处"+result);
        if (result != null) {
            return result;
        }

        randomCode = OtherUtil.randomCode();
        System.out.println("赋值" + randomCode);
        //验证码可以用随机生成
        try {
            SendSmsResponse sendSms = sendSms(String.valueOf(phone), randomCode);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public int insertSelective(User record, String code) {
        System.out.println("判断code" + randomCode);
        if (code.equals(randomCode)) {
            if (OtherUtil.isEmpty(record.getId())) {
                record.setUPassword(OtherUtil.MD5(record.getUPassword()));
                record.setUPetname(OtherUtil.getUname());
                loginRegDao.insertSelective(record);
                return 1;
            } else {
                return 0;
            }
        }
        return -1;
    }
}
