package com.aaa.house.dao;

import com.aaa.house.entity.Staff;
import com.aaa.house.utils.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: GZB
 * @Description:
 * @Date:Created in 10:29 2019/8/7
 * @Modified By:
 */
@Repository
//开启二级缓存 reids
@CacheNamespace(implementation = RedisCache.class)
public interface DStaffMapping {
    /**
     * 查询单个
     * @return
     */
    @Select("select * from staff where staff_id=#{staff_id} ")
    List<Staff> queryStaffId(Integer staff_id);

    /**
     * 修改
     * @param map
     * @return
     */
    @Update("update staff set staff_portrait=#{staff_portrait},staff_name=#{staff_name},staff_sex=#{staff_sex}," +
        "staff_phone=#{staff_phone},staff_card=#{staff_card} where staff_id=#{staff_id}")
    int updateStaff(Map map);

    //员工登录
    @Select("select * from staff where staff_num=#{staff_num}")
    Staff selectStaff(Map map);
}
