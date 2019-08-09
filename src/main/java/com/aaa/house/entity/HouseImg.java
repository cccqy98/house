package com.aaa.house.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * FileName: HouseImg
 * Author:   曹康
 * Date:     2019/8/1 21:07
 * Description: 房屋组图
 */
@Data
public class HouseImg implements Serializable {
    private String hid;
    private String himg;

    public HouseImg() {
    }
}
