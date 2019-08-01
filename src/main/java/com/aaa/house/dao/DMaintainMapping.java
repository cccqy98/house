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
     * 根据报修状态查询，两表联查
     * @return
     */
    @Select("<script>   select m.id,m.ma_user as maUser,m.ma_cause as maCause,m.ma_time maTime,m.ma_staff as maStaff,\n" +
            "m.ma_static as code_number,m.ma_false as maFalse,m.ma_house,c.code_state as ma_static\n" +
            "              FROM  maintain m join code c on c.code_number=m.ma_static\n" +
            "               join house h on h.house_uid=m.ma_house"+
            "    <where>\n" +
            "      <if test=\"ma_house!=null and ma_house!=''\">\n" +
            "        and (ma_house like \"%\"#{ma_house}\"%\")\n" +
            "      </if>\n" +
            "      <if test=\"code_number!=null and code_number!=''\">\n" +
            "       and ma_static =#{code_number}\n" +
            "      </if>\n" +
            "     and  c.code_type=11\n" +
            "         </where> " +
            "limit ${start},${pageSize}" +
            " </script>")
    List<Map>  queryStatic(Map map);

    /**
     * 根据审核状态查询
     * @return
     */
    @Select("<script> select m.id,m.ma_user as maUser,m.ma_cause as maCause,m.ma_time maTime,m.ma_staff as maStaff,m.ma_audit as code_number,\n" +
            "            m.ma_false as maFalse,m.ma_house,c.code_state as ma_audit\n" +
            "                         FROM  maintain m join code c on c.code_number=m.ma_audit\n" +
            "                         join house h on h.house_uid=m.ma_house\n" +
            "<where>" +
            "  <if test=\"ma_house!=null and ma_house!=''\">\n" +
            "        and (ma_house like \"%\"#{ma_house}\"%\")\n" +
            "      </if>\n" +

            "<if test=\"code_number!=null and code_number!=''\">\n" +
            "        and  m.ma_audit =#{code_number}\n" +
            "      </if> \n" +
            "and c.code_type=5"+
            "</where> " +
            "limit ${start},${pageSize}" +
            "</script>")
    List<Map>  queryAudit(Map map);

    /**
     *修改维修员工和不维修的原因
     * @return
     */
    @Update("UPDATE maintain set ma_false=#{maFalse} where id=#{id}")
    int updateMa(Maintain maintain);

    /**
     * 修改维修后状态
     * @param map
     * @return
     */
    @Update("update maintain set ma_staff=#{ma_staff},ma_static=2 where id=#{id}")
    int upMaintain(Map map);

    /**
     * 审核状态
     * @param map
     * @return
     */
    @Update("update maintain set ma_staff=#{ma_staff},ma_audit=2 where id=#{id}")
    int upAudit(Map map);

    /**
     * 报修状态数量
     * @param map
     * @return
     */
    @Select("<script> select count(*) FROM maintain m join code c  on c.code_number=m.ma_static" +
        " join house h on h.house_uid=m.ma_house"+
        "   <where>\n" +
        "      <if test=\"ma_house!=null and ma_house!=''\">\n" +
        "        and (ma_house like \"%\"#{ma_house}\"%\")\n" +
        "      </if>\n" +
        "      <if test=\"code_number!=null and code_number!=''\">\n" +
        "        and ma_static=#{code_number}\n" +
        "      </if>\n" +
        "     and  code_type=11\n" +
        "         </where> </script>")
    int queryPageCount(Map map);

    /**
     * 审核数量
     * @param map
     * @return
     */
    @Select("<script> select count(*) FROM maintain m join code c  on c.code_number=m.ma_audit\n" +
            " join house h on h.house_uid=m.ma_house" +
            " <where>" +
            "  <if test=\"ma_house!=null and ma_house!=''\">\n" +
            "        and (ma_house like \"%\"#{ma_house}\"%\")\n" +
            "      </if>\n" +

            "<if test=\"code_number!=null and code_number!=''\">\n" +
            "        and  m.ma_audit=#{code_number}\n" +
            "      </if> \n" +
            "and c.code_type=5 "+
            " </where> "+
             " </script>")
    int queryPageCount1(Map map);

    /**
     * 审核
     * @return
     */
    @Select("select code_number,code_state from code where code_type=5")
List<Map> queryCode();

    /**
     * 报修
     * @return
     */
    @Select("select code_number,code_state from code where code_type=11")
    List<Map> queryCode1();
}
