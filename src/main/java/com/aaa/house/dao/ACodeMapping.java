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
}
