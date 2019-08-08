package com.aaa.house.dao;

import com.aaa.house.entity.Staff;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: GZB
 * @Description:
 * @Date:Created in 10:29 2019/8/7
 * @Modified By:
 */
@Repository
public interface DStaffMapping {
    /**
     * 查询单个
     * @return
     */
    @Select("select * from staff where staff_id=#{staff_id} ")
    List<Staff> queryStaffId(Integer staff_id);

    /**
     * 修改
     * @param staff
     * @return
     */
    @Update("update staff set staff_portrait=#{staff_portrait},staff_name=#{staff_name},staff_sex=#{staff_sex}," +
        "staff_phone=#{staff_phone},staff_card=#{staff_card} where staff_id=#{staff_id}")
    int updateStaff(Staff staff);
}
