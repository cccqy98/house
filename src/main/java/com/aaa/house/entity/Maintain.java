package com.aaa.house.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Maintain
 * @Author 龚志博
 * @Date 2019/7/26 21:28
 * @Version 1.0
 */
@Data
public class Maintain implements Serializable {

    private Integer id;

    private String maUser;

    private String maCause;
@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date maTime;

    private Integer maAudit;

    private String maStaff;

    private Integer maStatic;

    private String maFalse;

private Integer code_number;
private String code_state;

    public Integer getCode_number() {
        return code_number;
    }

    public void setCode_number(Integer code_number) {
        this.code_number = code_number;
    }

    public String getCode_state() {
        return code_state;
    }

    public void setCode_state(String code_state) {
        this.code_state = code_state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaUser() {
        return maUser;
    }

    public void setMaUser(String maUser) {
        this.maUser = maUser == null ? null : maUser.trim();
    }

    public String getMaCause() {
        return maCause;
    }

    public void setMaCause(String maCause) {
        this.maCause = maCause == null ? null : maCause.trim();
    }

    public Date getMaTime() {
        return maTime;
    }

    public void setMaTime(Date maTime) {
        this.maTime = maTime;
    }

    public Integer getMaAudit() {
        return maAudit;
    }

    public void setMaAudit(Integer maAudit) {
        this.maAudit = maAudit;
    }

    public String getMaStaff() {
        return maStaff;
    }

    public void setMaStaff(String maStaff) {
        this.maStaff = maStaff == null ? null : maStaff.trim();
    }

    public Integer getMaStatic() {
        return maStatic;
    }

    public void setMaStatic(Integer maStatic) {
        this.maStatic = maStatic;
    }

    public String getMaFalse() {
        return maFalse;
    }

    public void setMaFalse(String maFalse) {
        this.maFalse = maFalse == null ? null : maFalse.trim();
    }

}
