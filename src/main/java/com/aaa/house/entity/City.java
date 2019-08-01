package com.aaa.house.entity;

import java.util.List;

/**
 * fileName:City
 * description:
 * author:guoxiaoxuan
 * createTime:2019/7/31 14:52
 * versoin:1.0.0
 */
public class City {
    private Integer id;
    private Integer codeType;
    private Integer codeNumber;
    private String codeState;
    private List<City> houseStreet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodeType() {
        return codeType;
    }

    public void setCodeType(Integer codeType) {
        this.codeType = codeType;
    }

    public Integer getCodeNumber() {
        return codeNumber;
    }

    public void setCodeNumber(Integer codeNumber) {
        this.codeNumber = codeNumber;
    }

    public String getCodeState() {
        return codeState;
    }

    public void setCodeState(String codeState) {
        this.codeState = codeState;
    }

    public List<City> getHouseStreet() {
        return houseStreet;
    }

    public void setHouseStreet(List<City> houseStreet) {
        this.houseStreet = houseStreet;
    }
}
