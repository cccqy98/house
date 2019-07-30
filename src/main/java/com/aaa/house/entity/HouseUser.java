package com.aaa.house.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * FileName: HouseUser
 * Author:   曹康
 * Date:     2019/7/29 20:49
 * Description: 客户发布房源表
 */
@Data
public class HouseUser implements Serializable {
    private int uh_id;
    private String uh_urban;
    private String uh_street;
    private String uh_district;
    private Integer uh_rent;
    private String uh_name;
    private String uh_phone;
    private String uh_state;
    private Integer uh_useid;

    public HouseUser() {
    }
}
