package com.aaa.house.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Classname：FtpConfig
 * @author: L_Fly
 * @Date: 2019/7/29  Time：10:01
 * @Version 1.0.0
 * 文件控制类
 */
@Component
@ConfigurationProperties(prefix = "ftp")
@PropertySource("classpath:config.properties")
@Data
public class FtpConfig {
    private String remoteIp;
    private Integer uploadPort;
    private String ftpUserName;
    private String ftpPassWord;
    private String remotePath;
    private String localPath;

    private String downLoadPath;


    //省略 getter setter 方法
}
