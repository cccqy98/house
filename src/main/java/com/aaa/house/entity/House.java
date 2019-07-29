package com.aaa.house.entity;

import java.util.Date;

/**
 * fileName:House
 * description:
 * author:guoxiaoxuan
 * createTime:2019/7/26 21:48
 * versoin:1.0.0
 */
public class House {
    private Integer id;

    private Integer houseUid;

    private String houseTitle;

    private Integer houseUrban;

    private Integer houseStreet;

    private String houseDistrict;

    private Integer houseLable;

    private Integer houseRent;

    private Integer houseState;

    private Integer houseArea;

    private Integer houseFloor;

    private Integer houseOrientation;

    private Date houseDate;

    private Integer houseLayout;

    private Integer houseStaffid;

    private Integer houseAudit;

    private String houseCertificate;

    private Integer rentStart;

    private Integer rentOver;

    private String start;

    private String pageSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHouseUid() {
        return houseUid;
    }

    public void setHouseUid(Integer houseUid) {
        this.houseUid = houseUid;
    }

    public String getHouseTitle() {
        return houseTitle;
    }

    public void setHouseTitle(String houseTitle) {
        this.houseTitle = houseTitle == null ? null : houseTitle.trim();
    }

    public Integer getHouseUrban() {
        return houseUrban;
    }

    public void setHouseUrban(Integer houseUrban) {
        this.houseUrban = houseUrban;
    }

    public Integer getHouseStreet() {
        return houseStreet;
    }

    public void setHouseStreet(Integer houseStreet) {
        this.houseStreet = houseStreet;
    }

    public String getHouseDistrict() {
        return houseDistrict;
    }

    public void setHouseDistrict(String houseDistrict) {
        this.houseDistrict = houseDistrict == null ? null : houseDistrict.trim();
    }

    public Integer getHouseLable() {
        return houseLable;
    }

    public void setHouseLable(Integer houseLable) {
        this.houseLable = houseLable;
    }

    public Integer getHouseRent() {
        return houseRent;
    }

    public void setHouseRent(Integer houseRent) {
        this.houseRent = houseRent;
    }

    public Integer getHouseState() {
        return houseState;
    }

    public void setHouseState(Integer houseState) {
        this.houseState = houseState;
    }

    public Integer getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(Integer houseArea) {
        this.houseArea = houseArea;
    }

    public Integer getHouseFloor() {
        return houseFloor;
    }

    public void setHouseFloor(Integer houseFloor) {
        this.houseFloor = houseFloor;
    }

    public Integer getHouseOrientation() {
        return houseOrientation;
    }

    public void setHouseOrientation(Integer houseOrientation) {
        this.houseOrientation = houseOrientation;
    }

    public Date getHouseDate() {
        return houseDate;
    }

    public void setHouseDate(Date houseDate) {
        this.houseDate = houseDate;
    }

    public Integer getHouseLayout() {
        return houseLayout;
    }

    public void setHouseLayout(Integer houseLayout) {
        this.houseLayout = houseLayout;
    }

    public Integer getHouseStaffid() {
        return houseStaffid;
    }

    public void setHouseStaffid(Integer houseStaffid) {
        this.houseStaffid = houseStaffid;
    }

    public Integer getHouseAudit() {
        return houseAudit;
    }

    public void setHouseAudit(Integer houseAudit) {
        this.houseAudit = houseAudit;
    }

    public String getHouseCertificate() {
        return houseCertificate;
    }

    public void setHouseCertificate(String houseCertificate) {
        this.houseCertificate = houseCertificate == null ? null : houseCertificate.trim();
    }

    public Integer getRentStart() {
        return rentStart;
    }

    public void setRentStart(Integer rentStart) {
        this.rentStart = rentStart;
    }

    public Integer getRentOver() {
        return rentOver;
    }

    public void setRentOver(Integer rentOver) {
        this.rentOver = rentOver;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
