package com.aaa.house.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * FileName: HouseLaIm
 * Author:   曹康
 * Date:     2019/8/2 9:18
 * Description: 房屋图片标签类
 */
@Data
public class HouseLaIm implements Serializable {
    private String house_id; //房屋id
    private String house_title;//房屋标题
    private String house_urban;//市
    private String house_street;//街道
    private String house_district;//详细地址
    private String house_rent;//租金
    private String house_state;//房屋状态 文字
    private String house_area;//面积
    private int house_floor;//楼层
    private String house_orintation;//朝向
    private String house_date;//发布时间
    private String house_layout;//布局
    private String house_staffid;//经济人编号
    private String house_audit;//审核状态
    private String house_cover;//房屋封面
    private String house_reason;//房屋驳回理由
    private String house_ditstaff;//当前登录人
    private String loh;//预约人数
    private String uh_rent;//住客期望租金

    private int houseState;//房屋状态 数字
    private int houseAudit;//审核状态 数字
    //标签
    private List<HouseLable> houseLables;
    //图片
    private List<HouseImg> houseImgs;
    //经济人
    private Staff staff;
    //房东
    private String id;//姓名
    private String u_name;//姓名
    private String u_phone;//手机号


    public HouseLaIm() {
    }
}
