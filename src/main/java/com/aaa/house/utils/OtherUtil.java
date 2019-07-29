package com.aaa.house.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Classname：OtherUtils
 * @author: L_Fly
 * @Date: 2019/7/26  Time：12:42
 * @Version 1.0.0
 */

public class OtherUtil{
    /**
     * 生成六位随机数
     * @return string
     */
    public static String randomCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        System.out.println("----------------------验证码"+str.toString());
        return str.toString();
    }

    /**
     * 判断字符串是否为空
     * @param str 字符串
     * @return boolean
     */
    public static boolean isEmpty(Object str) {
        if (str == null || str.equals("") || str.equals("null")) {
            return true;
        }
        return false;
    }

    /**随机生成用户名
     * @return
     */
    public static String getUname(){
        String randomcode = "";
        for(int i=0;i<6;i++) {
            //52个字母与6个大小写字母间的符号；范围为91~96
            int value = (int)(Math.random()*58+65);
            while(value>=91 && value<=96)
                value = (int)(Math.random()*58+65);
            randomcode = randomcode + (char)value;

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













    /**
     * 获取现在时间
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
     * @return
     */
    public static String GetCnum(){
        String t = getStringDate();
        int x=(int)(Math.random()*900)+100;
        String serial = t + x;
        return serial;
    }

    public static void main(String[] args) {
        System.out.println(GetCnum());

    }








}
