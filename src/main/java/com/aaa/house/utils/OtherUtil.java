package com.aaa.house.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Classname：OtherUtils
 * @author: L_Fly
 * @Date: 2019/7/26  Time：12:42
 * @Version 1.0.0
 */

public class OtherUtil {
    @Autowired//文件工具类
    private FtpUtil ftpUtil;
    @Autowired//文件配置
    private FtpConfig ftpConfig;
    @Autowired//spring core io里面提供的资源加载器
    private ResourceLoader resourceLoader;

    /**
     * 生成六位随机数
     *
     * @return string
     */
    public static String randomCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        System.out.println("----------------------验证码" + str.toString());
        return str.toString();
    }

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isEmpty(Object str) {
        if (str == null || str.equals("") || str.equals("null")) {
            return true;
        }
        return false;
    }

    /**
     * 随机生成用户名
     *
     * @return String
     */
    public static String getUname() {
        String randomcode = "";
        for (int i = 0; i < 6; i++) {
            //52个字母与6个大小写字母间的符号；范围为91~96
            int value = (int) (Math.random() * 58 + 65);
            while (value >= 91 && value <= 96)
                value = (int) (Math.random() * 58 + 65);
            randomcode = randomcode + (char) value;
        }
        return randomcode;
    }

    /**
     * MD5 加密密码
     *
     * @param password 密码
     * @param salt     手机号
     * @return String
     */
    public static final String MD5(String password, String salt) {
        //加密方式
        String hashAlgorithmName = "MD5";
        //盐：为了即使相同的密码不同的盐加密后的结果也不同
        ByteSource byteSalt = ByteSource.Util.bytes(salt);
        //密码
        Object source = password;
        //加密次数
        int hashIterations = 1024;
        SimpleHash result = new SimpleHash(hashAlgorithmName, source, byteSalt, hashIterations);
        return result.toString();
    }

    public static boolean checkIDCard(String id, String name) {
        String host = "https://eid.shumaidata.com";
        String path = "/eid/check";
        String method = "POST";
        String appcode = "403b99930ef14d5e9b35474b9da45eac";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
//        System.out.println(headers);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("idcard", id);
        querys.put("name", name);
        Map<String, String> bodys = new HashMap<String, String>();
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = IDCheckUtil.doPost(host, path, method, headers, querys, bodys);
//            System.out.println(response.toString());

            //获取response的body
            String res = EntityUtils.toString(response.getEntity());
//            System.out.println(result);
            com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(res);
            String code = jsonObject.getString("code");

            if (code.equals("0")) {
                String data = jsonObject.getString("result");
                JSONObject jsondata = JSON.parseObject(data);
                String token = jsondata.getString("res");
                if (token.equals("1")) {
                    return true;
                }
                return false;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式yyyyMMddHHmmss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMHH");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 由年月日时分秒+3位随机数
     * 生成编号
     *
     * @return
     */
    public static String GetCnum() {
        String t = getStringDate();
        int x = (int) (Math.random() * 900) + 100;
        String serial = t + x;
        return serial;
    }

    public static void main(String[] args) {
        System.out.println(GetCnum());
    }
}
