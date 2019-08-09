package com.aaa.house.dao;


import com.aaa.house.entity.HouseLaIm;
import com.aaa.house.utils.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
//开启二级缓存 reids
@CacheNamespace(implementation = RedisCache.class)
public interface DHouseMapper {

    /**
     *
     * @param map
     * @return
     */
    List<Map> getHouse1(Map map);
    @Select("<script> select count(*) from house h join code c on c.code_number=h.house_audit" +
            "<where>"+
            "<if test=\"code_number!=null and code_number!=''\">\n" +
            "        and  house_audit=#{code_number}\n" +
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

    /**
     * 不审核的原因
     * @return
     */
    @Update("update house set house_reason=#{house_reason},house_ditstaff=null,house_audit=3 where house_id=#{house_id}")
    int updateHouse(HouseLaIm houseLaIm);

    @Update("update house set house_ditstaff=#{house_ditstaff},house_reason=null,house_audit=2 where house_id=#{house_id}")
    int upAudit1(Map map);

}
