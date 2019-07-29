package com.aaa.house.entity;

import java.io.Serializable;

/**
 * FileName: Staff
 * Author:   曹康
 * Date:     2019/7/26 9:50
 * Description: 员工实体类
 */
public class Staff implements Serializable {
    private int staff_id;
    private String staff_num;//员工编号
    private String staff_name;//员工名字
    private int staff_phone;//电话
    private int staff_sex;//性别
    private String staff_password;//密码
    private int staff_state;//状态
    private String staff_portrait;//路径

    public Staff() {
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public String getStaff_num() {
        return staff_num;
    }

    public void setStaff_num(String staff_num) {
        this.staff_num = staff_num;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public int getStaff_phone() {
        return staff_phone;
    }

    public void setStaff_phone(int staff_phone) {
        this.staff_phone = staff_phone;
    }

    public int getStaff_sex() {
        return staff_sex;
    }

    public void setStaff_sex(int staff_sex) {
        this.staff_sex = staff_sex;
    }

    public String getStaff_password() {
        return staff_password;
    }

    public void setStaff_password(String staff_password) {
        this.staff_password = staff_password;
    }

    public int getStaff_state() {
        return staff_state;
    }

    public void setStaff_state(int staff_state) {
        this.staff_state = staff_state;
    }

    public String getStaff_portrait() {
        return staff_portrait;
    }

    public void setStaff_portrait(String staff_portrait) {
        this.staff_portrait = staff_portrait;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staff_id=" + staff_id +
                ", staff_num='" + staff_num + '\'' +
                ", staff_name='" + staff_name + '\'' +
                ", staff_phone=" + staff_phone +
                ", staff_sex=" + staff_sex +
                ", staff_password='" + staff_password + '\'' +
                ", staff_state=" + staff_state +
                ", staff_portrait='" + staff_portrait + '\'' +
                '}';
    }
}
