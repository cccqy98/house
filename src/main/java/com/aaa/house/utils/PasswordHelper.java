package com.aaa.house.utils;

import com.aaa.house.entity.Staff;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * FileName: PasswordHelper
 * Author:   曹康
 * Date:     2019/7/27 12:30
 * Description: md5加密
 */
public class PasswordHelper {
    private static String hashAlgorithmName="MD5";
    private static Integer hashIterations=1024;


    public static void encryptPassword(Staff staff){
        //原始密码
        String password = staff.getStaff_password();
        //盐 账号加密
        ByteSource bytes = ByteSource.Util.bytes(staff.getStaff_num());
        String credentials=new SimpleHash(hashAlgorithmName,password,bytes,hashIterations).toHex();
        //设置新密码
        staff.setStaff_password(credentials);
    }


    /**
     * 通用md5
     */
    public static String passwordStaff(String name,String password){
        //盐 账号加密
        ByteSource bytes = ByteSource.Util.bytes(name);
        String credentials=new SimpleHash(hashAlgorithmName,password,bytes,hashIterations).toHex();
        //返回新密码
        return credentials;
    }

}
