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

    private Integer userId;

    private String houseTitle;

    private Integer houseUrban;

    private Integer houseStreet;

    private String houseDistrict;

    private Integer houseRent;

    private Integer houseState;

    private Integer houseArea;

    private Integer houseFloor;

    private Integer houseOrientation;

    private Date houseDate;

    private Integer newstime;

    private Integer houseLayout;

    private String houseStaffid;

    private Integer houseAudit;

    private String houseCertificate;

    private String houseCover;

    private String houlayout;

    private String houurban;

    private String houstreet;

    private String houorientation;

    private List<String> houseLabel;

    private List<String> houseImgs;

    private Staff houStaff;


}
