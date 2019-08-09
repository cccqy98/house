package com.aaa.house.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * FileName: HouseLable
 * Author:   曹康
 * Date:     2019/8/1 19:12
 * Description: 房屋标签表
 */
@Data
public class HouseLable implements Serializable {
    private String la_id;
    private String ho_id;

    public HouseLable() {
    }
}
