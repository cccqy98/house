package com.aaa.house.dao;


import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface DHouseMapper {

    @Select("<script> select count(*) from house h join code c on c.code_number=h.house_audit" +
            "<where>"+
            "<if test=\"code_number!=null and code_number!=''\">\n" +
            "        and  h.house_audit=#{code_number}\n" +
            "      </if> \n" +
            "and code_type=5"+
            "</where>"+
    "</script>")
    int queryPageCount(Map map);
    /**
     * 查询全部审核状态
     * @return
     */
    @Select("select code_number,code_state from code where code_type=5")
   List<Map> queryCode2();



    @Update("update house set house_staffid=#{house_staffid},ma_audit=2 where id=#{id}")
    int upAudit1(Map map);

}
