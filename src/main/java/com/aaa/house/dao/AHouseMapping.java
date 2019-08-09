package com.aaa.house.dao;

import com.aaa.house.entity.HouseImg;
import com.aaa.house.entity.HouseLaIm;
import com.aaa.house.entity.HouseLable;
import com.aaa.house.entity.HouseUser;

import com.aaa.house.utils.RedisCache;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
//开启二级缓存 reids
@CacheNamespace(implementation = RedisCache.class)
public interface AHouseMapping {

    /**
     *查询未联系的房源 分页加模糊查询
     * @return
     */
    @Select("<script> select hu.uh_id,aa.code_state as uh_urban,bb.code_state as uh_street,hu.uh_district,hu.uh_rent,hu.uh_name,hu.uh_phone,cc.code_state as uh_state,hu.uh_useid \n" +
            "            from house_user hu JOIN code aa\n" +
            "            on hu.uh_urban=aa.code_number\n" +
            "            join code bb\n" +
            "           on hu.uh_street=bb.code_number\n" +
            "            join code cc\n" +
            "            on hu.uh_state=cc.code_number\n" +
            "            <where>\n" +
            "                    <if test=\"uh_name !=null and uh_name!=''\">\n" +
            "                        and uh_name like \"%\"#{uh_name}\"%\"\n" +
            "                    </if>\n" +
            "                    <if test=\"uh_district !=null and uh_district!=''\">\n" +
            "                        and uh_district like \"%\"#{uh_district}\"%\"\n" +
            "                    </if>\n" +
            "                    and cc.code_type=4\n" +
            "            </where>\n" +
            "         limit ${start},${pageSize}"+
            "        </script>")
    List<HouseUser> getHouseUserAll(Map map);

    /**
     * 查询总条数
     * @param map
     * @return
     */
    @Select("<script>select count(*) from house_user hu JOIN code aa\n" +
            "            on hu.uh_urban=aa.code_number\n" +
            "            join code bb\n" +
            "           on hu.uh_street=bb.code_number\n" +
            "            join code cc\n" +
            "            on hu.uh_state=cc.code_number\n" +
            "            <where>\n" +
            "                    <if test=\"uh_name !=null and uh_name!=''\">\n" +
            "                        and uh_name like \"%\"#{uh_name}\"%\"\n" +
            "                    </if>\n" +
            "                    <if test=\"uh_district !=null and uh_district!=''\">\n" +
            "                        and uh_district like \"%\"#{uh_district}\"%\"\n" +
            "                    </if>\n" +
            "                    and cc.code_type=4\n" +
            "            </where></script>")
    int getHouseUserquall(Map map);


    /**
     * 联系房源
     * @return
     */
    @Update("UPDATE house_user set uh_state=1,uh_staff=#{uh_staff} WHERE uh_id=#{uh_id}")
    int upHouseUser(Map map);

    /**
     *查询当前经济人联系的房源
     * @param uh_staff
     * @return
     */
    @Select("select uh_useid,uh_name from house_user where  uh_staff=#{uh_staff} and uh_state=1 group by uh_useid")
    List<Map> getStaffUser(String uh_staff);

    /**
     * 查询当前房东的房源
     * @param map
     * @return
     */
    @Select("SELECT uh_id,uh_urban,uh_street,uh_district,uh_name FROM house_user where uh_useid=#{uh_useid} and uh_addhouse=1")
    List<HouseUser> getUserHouse(Map map);


    //******************************查询***********************************************
    /**
     * 查询所有房屋  嵌套查询**一对多
     * @return
     */
    List<HouseLaIm> getHouse(Map map);

    /**
     * 查询所有房屋数量
     * @return
     */
    @Select("<script>SELECT COUNT(*)\n" +
            "from house h join code aa\n" +
            "on h.house_urban=aa.code_number\n" +
            "join code bb\n" +
            "on h.house_street=bb.code_number\n" +
            "join code cc\n" +
            "on h.house_state=cc.code_number\n" +
            "join code dd\n" +
            "on h.house_orientation=dd.code_number\n" +
            "join code ee\n" +
            "on h.house_layout=ee.code_number\n" +
            "join code ff\n" +
            "on h.house_audit=ff.code_number\n" +
            "\n" +
            "join staff\n" +
            "on h.house_staffid=staff_num\n" +
            "<where>\n" +
            "            <if test=\"house_state != null and house_state !=''\">\n" +
            "                and house_state = #{house_state}\n" +
            "            </if>\n" +
            "            <if test=\"house_district !=null and house_district !=''\">\n" +
            "                and house_district like \"%\"#{house_district}\"%\"\n" +
            "            </if>\n" +
            "        </where>\n" +
            "and cc.code_type=8 and dd.code_type=6 and ee.code_type=7 and ff.code_type=5 </script>")
    int getHouseNum(Map map);

    //*******************************房屋修改*****************************************

    /**
     * 全部房屋修改
     * @return
     */
    @Update("update house set house_title=#{house_title},house_district=#{house_district}," +
            "house_rent=#{house_rent},house_state=#{houseState},house_audit=#{houseAudit} where house_id=#{house_id}")
    int updaHouse(Map map);


    //*******************************************房屋假删除***************************************************

    /**
     * 房屋假删除
     * @return
     */
    @Update("update house set house_delete=2 where house_id=#{house_id}")
    int deleteHouse(Map map);



    //*******************************************房屋添加***************************************************


    /**
     * 添加房屋
     * @return
     */
    @Insert("insert into house(house_uid,house_title,house_urban,house_street,house_district,house_rent,house_area,house_floor,house_orientation,house_date,house_layout,house_staffid,house_certificate,house_cover,house_id)\n" +
            "VALUES(#{uh_name},#{house_title},#{uh_urban},#{uh_street},#{uh_district},#{house_rent},#{house_area},#{house_floor},#{house_orientation},#{house_date},#{house_layout},#{staff_num},#{headPic1},#{headPic},#{house_id})")
    int setHouse(Map map);

    /**
     * 修改为已经添加
     * @return
     */
    @Update("update house_user set uh_addhouse=2 where uh_id=#{uh_name}")
    int updateHouseUser(String uh_name);

    /**
     * 添加房屋标签表
     * @param
     * @return
     */
    @Insert("<script> insert into house_lable(la_id,ho_id) values\n" +
            "\n" +
            "    <foreach collection=\"list\" item=\"item\" separator=\",\">\n" +
            "        (#{item.la_id},#{item.ho_id})\n" +
            "    </foreach></script>")
    int setHouseLable(List<HouseLable> houseLables);

    /**
     * 添加房屋组图表
     * @return
     */

    @Insert("<script>insert into house_img (hid,himg) values\n" +
            "    <foreach collection=\"list\" item=\"item\" separator=\",\">\n" +
            "        (#{item.hid},#{item.himg})\n" +
            "    </foreach></script>")
    int setHouseImg(List<HouseImg> list);
    //*******************************************查询我的房屋***************************************************

    /**
     * 经济人查询我的房屋
     * @param map
     * @return
     */
    List<HouseLaIm> getMyHouse(Map map);

    /**
     * 我的房屋数量
     * @param map
     * @return
     */
    @Select("<script>  SELECT COUNT(*) \n" +
            "        from house\n" +
            "       \n" +
            "\n" +
            "         h join code aa\n" +
            "        on h.house_urban=aa.code_number\n" +
            "        join code bb\n" +
            "        on h.house_street=bb.code_number\n" +
            "        join code cc\n" +
            "        on h.house_state=cc.code_number\n" +
            "        join code dd\n" +
            "        on h.house_orientation=dd.code_number\n" +
            "        join code ee\n" +
            "        on h.house_layout=ee.code_number\n" +
            "        join code ff\n" +
            "        on h.house_audit=ff.code_number\n" +
            " <where>\n" +
            "            <if test=\"house_state != null and house_state !=''\">\n" +
            "                and house_state = #{house_state}\n" +
            "            </if>\n" +
            "            <if test=\"house_district !=null and house_district !=''\">\n" +
            "                and house_district like \"%\"#{house_district}\"%\"\n" +
            "            </if>\n" +
            "            and house_delete=1 and house_staffid=#{house_staffid} and  cc.code_type=8 and dd.code_type=6 and ee.code_type=7 and ff.code_type=5 \n" +
            "        </where></script>")
    int getMyhouseNum(Map map);


    /**
     * 我的房屋修改
     * @return
     */
    @Update("update house set house_area=#{house_area},house_floor=#{house_floor}," +
            "house_title=#{house_title},house_district=#{house_district}," +
            "house_rent=#{house_rent} where house_id=#{house_id}")
    int updaMyHouse(Map map);

    /**
     * 房屋下架
     * @param map
     * @return
     */
    @Update("update house set house_state=4 where house_id=#{house_id}")
    int upMyHouseXiajia(Map map);

    /**
     * 预约人数+信息+分页=房源出租
     * @return
     */
    List<HouseLaIm> getLookMyHouse(Map map);

    /**
     * 获取 房屋发布的数量
     * @param map
     * @return
     */
    @Select("<script>  SELECT COUNT(*) \n" +
            "        from house\n" +
            "       \n" +
            "\n" +
            "         h join code aa\n" +
            "        on h.house_urban=aa.code_number\n" +
            "        join code bb\n" +
            "        on h.house_street=bb.code_number\n" +
            "        join code cc\n" +
            "        on h.house_state=cc.code_number\n" +
            "        join code dd\n" +
            "        on h.house_orientation=dd.code_number\n" +
            "        join code ee\n" +
            "        on h.house_layout=ee.code_number\n" +
            "        join code ff\n" +
            "        on h.house_audit=ff.code_number\n" +
            " <where>\n" +
            "            <if test=\"house_state != null and house_state !=''\">\n" +
            "                and house_state = #{house_state}\n" +
            "            </if>\n" +
            "            <if test=\"house_district !=null and house_district !=''\">\n" +
            "                and house_district like \"%\"#{house_district}\"%\"\n" +
            "            </if>\n" +
            "           and house_state=2 and house_delete=1 and house_staffid=#{house_staffid} and  cc.code_type=8 and dd.code_type=6 and ee.code_type=7 and ff.code_type=5 \n" +
            "        </where></script>")
    int getLookMyHouseNum(Map map);

    /**
     * 房屋发布
     * @return
     */
    @Update("update house set house_state=2 where house_id=#{house_id}")
    int upMYHouse(Map map);

    /**
     * 查询我的租客
     * @param map
     * @return
     */
    @Select("select * from pact where pact_houseid=#{house_id}")
    Map getpact(Map map);

    //*******************************************出租房屋***************************************************



    /**
     * 获取该房屋的预约信息
     * @return
     */

    @Select("select loh.look_state as lookstate,loh.look_userid,loh.look_house,loh.look_data,cc.code_state as look_state," +
            "user.u_name,user.u_phone,u_card,u_sex,hu.uh_rent,house_rent\n" +
            "from look_house loh\n" +
            "JOIN code cc\n" +
            "on loh.look_state=cc.code_number\n" +
            "join user \n" +
            "on loh.look_userid=user.id\n" +
            "join house_user hu\n" +
            "on loh.look_house=hu.uh_id\n" +
            "join house\n" +
            "on loh.look_house=house.house_id\n" +
            "where loh.look_house=#{look_house} and cc.code_type=10")
    List<Map> getUserLookHouse(Map map);

    /**
     * 修改允许看房
     * @param map
     * @return
     */
    @Update("UPDATE look_house set look_state=2 WHERE look_userid=#{look_userid} and look_house=#{look_house}")
    int updalookHouse(Map map);

    /**
     * 添加看房时间和状态
     * @param map
     *@return
     */
    @Update("update look_house set look_state=#{lookstate},look_data=#{look_date} where look_userid=#{look_userid} and look_house=#{look_house}")
    int upLookHousestate(Map map);

    /**
     * 删除无意租的看房用户
     * @param map
     * @return
     */
    @Delete("DELETE from look_house where look_userid=#{look_userid} and look_house=#{look_house}")
    int deLookHouse(Map map);

    /**
     * 添加合同基础信息
     * @param map
     * @return
     */
    @Insert("INSERT into pact(pact_usename,pact_uphone,pact_staffname,pact_sphone,pact_houseid,pact_stacard,pact_usecard)\n" +
            "VALUES(#{u_name},#{u_phone},#{staff_name},#{staff_phone},#{look_house},#{staff_card},#{u_card}) ")
    int setPact(Map map);

    /**
     * 合同添加时间
     * @param map
     * @return
     */
    @Update("update pact set pact_begin=#{pact_begin},pact_end=#{pact_end},pact_cost=#{pact_cost},pact_poundage=#{pact_poundage} " +
            " where pact_houseid=#{look_house}")
    int upPact(Map map);

    /**
     * 修改房屋为已出租状态
     * @return
     */
    @Update("update house set house_state=3 where house_id=#{look_house}")
    int upHousestate(Map map);

}
