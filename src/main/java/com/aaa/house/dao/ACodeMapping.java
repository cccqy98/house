package com.aaa.house.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 字典表查
 */
public interface ACodeMapping {

    /**
     * 查询所有街道
     * @return
     */
    @Select("SELECT code_number,code_state from code where code_type=#{code_type}")
    List<Map> getHouseCode(Map map);

    /**
     * 查询所有房屋状态
     * @return
     */
    @Select("select code_number,code_state from code where code_type=8")
    List<Map> getHouseState();

    /**
     * 查询审核状态
     * @return
     */
    @Select("select code_number,code_state from code where code_type=5")
    List<Map> getHouseAudit();

    /**
     * 查询员工状态
     * @return
     */
    @Select("select * from code where code_type=2")
    List<Map> getUserState();

    /**
     * 查询员工性别
     * @return
     */
    @Select("select * from code where code_type=1")
    List<Map>getUserSet();

}
