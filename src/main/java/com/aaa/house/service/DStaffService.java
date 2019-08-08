package com.aaa.house.service;

import com.aaa.house.entity.Staff;

import java.util.List;

/**
 * @Author: GZB
 * @Description:
 * @Date:Created in 16:37 2019/8/7
 * @Modified By:
 */
public interface DStaffService {
    /**
     * 查询单个
     * @return
     */
    List<Staff> queryStaffId();

    /**
     * 修改
     * @param staff
     * @return
     */
    int updateStaff(Staff staff);
}
