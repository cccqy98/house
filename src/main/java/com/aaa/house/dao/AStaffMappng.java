package com.aaa.house.dao;

import com.aaa.house.entity.Staff;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 员工 用户增删改查
 * 曹康
 */
@Repository
public interface AStaffMappng {

    //员工登录
    @Select("select * from staff where staff_num=#{staff_num} and staff_password=#{staff_password} and staff_state=1")
    Staff selectStaff(Staff staff);

    //查询所有员工
    @Select("select * from staff")
    List<Staff> selectStaffAll();

    //员工添加
    @Insert("INSERT INTO staff (staff_num,staff_name,staff_phone,staff_sex,staff_password,staff_portrait)\n" +
            "VALUES(#{staff_num},#{staff_name},#{staff_phone},#{staff_sex},#{staff_password},#{staff_portrait})")
    int insertStaff(Staff staff);

    //修改员工信息
    int updateStaff(Staff staff);


    /*9******************************用户****************************************9*/
    /**
     * 查询所有用户
     * @return
     */
    @Select("<script>  select u.id,u.u_petname,u.u_state as ustate,u.u_sex as usex,u.u_petname,u.u_name,aa.code_state as u_sex,u.u_phone,u.u_card,bb.code_state as u_state,cc.code_state as u_cardstate\n" +
            "    from user u\n" +
            "    join code aa\n" +
            "    on u.u_sex=aa.code_number\n" +
            "\n" +
            "    join code bb\n" +
            "    on u.u_state=bb.code_number\n" +
            "\n" +
            "    join code cc\n" +
            "    on u.u_cardstate=cc.code_number\n" +
            "\n" +
            "    <where>\n" +
            "        <if test=\"u_name != null and u_name !=''\">\n" +
            "           and u_name like \"%\"#{u_name}\"%\"\n" +
            "        </if>\n" +
            "        and aa.code_type=1 and bb.code_type=2 and cc.code_type=100\n" +
            "    </where> limit #{start},#{pageSize}</script>")
    List<Map> getUser(Map map);

    /**
     * 查询用户数量
     * @return
     */
    @Select("<script>  select count(*)\n" +
            "    from user u\n" +
            "    join code aa\n" +
            "    on u.u_sex=aa.code_number\n" +
            "\n" +
            "    join code bb\n" +
            "    on u.u_state=bb.code_number\n" +
            "\n" +
            "    join code cc\n" +
            "    on u.u_cardstate=cc.code_number\n" +
            "\n" +
            "    <where>\n" +
            "        <if test=\"u_name != null and u_name !=''\">\n" +
            "           and u_name like \"%\"#{u_name}\"%\"\n" +
            "        </if>\n" +
            "        and aa.code_type=1 and bb.code_type=2 and cc.code_type=100\n" +
            "    </where></script>")
    int getUserNum(Map map);

    /**
     * 修改用户信息
     * @return
     */
    @Update("update user set u_name=#{u_name},u_sex=#{usex},u_state=#{ustate} where id =#{id}")
    int upUser(Map map);

}
