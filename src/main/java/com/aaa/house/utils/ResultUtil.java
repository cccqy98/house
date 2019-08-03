package com.aaa.house.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Classname：ResultUtilUtil
 * @author: L_Fly
 * @Date: 2019/7/26  Time：14:04
 * @Version 1.0.0
 * 交互实体类
 */
@Getter
@Setter
@ToString
public class ResultUtil {

    private Integer code;//状态码
    private String msg;
    private Object object;//数据
    private Object house;//数据
    private Integer count;

    public ResultUtil() {
    }

    public ResultUtil(Integer code, String msg, Object object) {
        this.code = code;
        this.msg = msg;
        this.object = object;
    }

    public ResultUtil(Integer code, String msg, Object object, Object house) {
        this.code = code;
        this.msg = msg;
        this.object = object;
        this.house = house;
    }

    public ResultUtil(Integer code, String msg, Object object, Integer count) {
        this.code = code;
        this.msg = msg;
        this.object = object;
        this.count = count;
    }
}
