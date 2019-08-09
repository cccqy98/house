package com.aaa.house.entity;

import java.io.Serializable;

public class BHouseUser implements Serializable {

    //int(200) NOT NULL客户布房源
    private int uh_id;
    //int(200) NULL市区
    private int uh_urban;
    //int(200) NULL街道
    private int uh_street;
    //varchar(200) NULL详细地址
    private String uh_district;
    //int(200) NULL期望租金
    private int uh_rent;
    //varchar(200) NULL客户称呼
    private String uh_name;
    //varchar(200) NULL联系电话
    private String uh_phone;
    //int(200) NULL状态是否有经纪人联系
    private String uh_state;
    //int(200) NULL关联哪个用户的房
    private String uh_useid;
    //varchar(200) NULL联系的经济人
    private String uh_staff;

    public int getUh_id() {
        return uh_id;
    }

    public void setUh_id(int uh_id) {
        this.uh_id = uh_id;
    }

    public int getUh_urban() {
        return uh_urban;
    }

    public void setUh_urban(int uh_urban) {
        this.uh_urban = uh_urban;
    }

    public int getUh_street() {
        return uh_street;
    }

    public void setUh_street(int uh_street) {
        this.uh_street = uh_street;
    }

    public String getUh_district() {
        return uh_district;
    }

    public void setUh_district(String uh_district) {
        this.uh_district = uh_district;
    }

    public int getUh_rent() {
        return uh_rent;
    }

    public void setUh_rent(int uh_rent) {
        this.uh_rent = uh_rent;
    }

    public String getUh_name() {
        return uh_name;
    }

    public void setUh_name(String uh_name) {
        this.uh_name = uh_name;
    }

    public String getUh_phone() {
        return uh_phone;
    }

    public void setUh_phone(String uh_phone) {
        this.uh_phone = uh_phone;
    }

    public String getUh_state() {
        return uh_state;
    }

    public void setUh_state(String uh_state) {
        this.uh_state = uh_state;
    }

    public String getUh_useid() {
        return uh_useid;
    }

    public void setUh_useid(String uh_useid) {
        this.uh_useid = uh_useid;
    }

    public String getUh_staff() {
        return uh_staff;
    }

    public void setUh_staff(String uh_staff) {
        this.uh_staff = uh_staff;
    }
}
