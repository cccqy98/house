package com.aaa.house.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * fileName:House
 * description:
 * author:guoxiaoxuan
 * createTime:2019/7/26 21:48
 * versoin:1.0.0
 */
@Data
public class House {
    private Integer id;

    private Integer houseId;//房间id

    private Integer houseUid;//房东id

    private Integer userId;//用户

    private String houseTitle;//房屋标题

    private Integer houseUrban;//市区编号

    private Integer houseStreet;//街道编号

    private String houseDistrict;//详细地址

    private Integer houseRent;//房屋租金

    private Integer houseState;//房屋状态编号

    private Integer houseArea;//房屋面积

    private Integer houseFloor;//楼层

    private Integer houseOrientation;//房屋朝向编号

    private Date houseDate;//发布日期

    private Integer newstime;//发布距离天数

    private Integer houseLayout;//房屋布局编号

    private String houseStaffid;//经纪人编号

    private Integer houseAudit;//审核状态编号

    private String houseCertificate;//房产证图路径

    private String houseCover;//房屋封面图路径

    private String houlayout;//房屋布局

    private String houurban;//市

    private String houstreet;//街道

    private String houorientation;//房屋朝向

    private List<String> houseLabel;//房屋标签

    private List<String> houseImgs;//房屋图片



}
