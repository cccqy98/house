package com.aaa.house.dao;

import com.aaa.house.entity.HouseUser;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
/*//开启二级缓存 reids
@CacheNamespace(implementation = RedisCache.class)*/
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
    @Select("select uh_useid,uh_name from house_user where  uh_staff=#{uh_staff} and uh_state=2 group by uh_useid")
    List<Map> getStaffUser(String uh_staff);

    /**
     * 查询当前房东的房源
     * @param map
     * @return
     */
    @Select("SELECT uh_id,uh_urban,uh_street,uh_district,uh_name FROM house_user where uh_useid=#{uh_useid}")
    List<HouseUser> getUserHouse(Map map);

}
