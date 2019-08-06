package com.aaa.house.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: GZB
 * @Description:
 * @Date:Created in 8:45 2019/8/6
 * @Modified By:
 */
@Repository
public interface EchartsMapping {
    @Select("<script>select <if test='month!=null and month!=0'> date_format(p.pact_begin,'%m-%d') </if>  <if test='month==null or month==0'> date_format(p.pact_begin,'%Y-%m') </if> as months,(sum(pact_poundage)) pp,count(*) c from pact p " +
            "where  date_format(p.pact_begin,'%Y')=#{year}" +
            "<if test='month!=null and month!=0'> and date_format(p.pact_begin,'%m')=#{month} </if>"+
            " group by <if test='month!=null and month!=0'> date_format(p.pact_begin,'%m-%d') </if>  <if test='month==null or month==0'> date_format(p.pact_begin,'%Y-%m')</if>" +
            " order by  <if test='month!=null and month!=0'> date_format(p.pact_begin,'%m-%d') </if>  <if test='month==null or month==0'> date_format(p.pact_begin,'%Y-%m')</if></script>")
List<Map> queryDPact(Map map);
}
