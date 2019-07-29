package com.aaa.house.dao;

import com.aaa.house.entity.Maintain;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName Maintain
 * @Author 龚志博
 * @Date 2019/7/26 21:28
 * @Version 1.0
 */
@Repository
public interface DMaintainMapping {
    /**
     * 查询全部报修信息
     * @return
     */
    @Select("select * from maintain")
List<Maintain> queryAll();

    /**
     * 录入报修信息
     * @return
     */
    @Insert("insert into maintain (id,ma_user,ma_cause,ma_time,ma_audit,ma_staff,ma_static,ma_false)) " +
            " values(#{id},#{maUser},#{maCause},#{maTime},#{maAudit},#{maStaff},#{maStatic},#{maFalse})")
    int AddMaintain(Maintain maintain);

    /**
     * 根据报修状态查询，两表联查
     * @return
     */
    @Select("select m.*,code_state FROM code c join maintain m on c.code_number=m.ma_static WHERE c.code_type=11;")
    List<Map>  queryStatic(Map map);

    /**
     * 根据审核状态查询
     * @return
     */
    @Select("select m.*,code_state FROM code c join maintain m on c.code_number=m.ma_audit WHERE   c.code_type=5;")
    List<Map>  queryAudit(Map map);

    /**
     * 删除已维修和审核未通过报修信息
     * @return
     */
    @Delete("delete from maintain where ma_static=3 or ma_audit=3")
    int delete();

    /**
     *修改维修员工和不维修的原因
     * @return
     */
    @Update("UPDATE maintain set ma_staff=#{maStaff},ma_false=#{maFalse}")
    int updateMa(Maintain maintain);
}
