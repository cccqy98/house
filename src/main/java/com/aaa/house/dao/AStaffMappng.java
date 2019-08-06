package com.aaa.house.dao;

import com.aaa.house.entity.Staff;
import com.aaa.house.entity.StaffRole;
import org.apache.ibatis.annotations.*;
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

    /*9*******************************员工信息******************************************6*/

    /**
     * 查询所有员工信息
     * @return
     */
    List<Staff> getStaffAll(Map map);


    /**
     * 查询员工数量
     * @return
     */
    @Select("<script> SELECT  count(*)\n" +
            "        from staff\n" +
            "        join `code` aa\n" +
            "        on staff.staff_sex=aa.code_number\n" +
            "        join `code` bb\n" +
            "        on staff.staff_state=bb.code_number\n" +
            "        \n" +
            "<where>\n" +
            "<if test=\"staff_name!=null and staff_name !=''\">\n" +
            "    and staff_name like \"%\"#{staff_name}\"%\"\n" +
            "</if>\n" +
            "and aa.code_type=1 and bb.code_type=3\n" +
            "</where></script>")
    int getStaffNum(Map map);

    /**
     * 添加员工
     * @param map
     * @return
     */
    int insertStaff(Map map);



    /**
     * 获取最后一个编号
     * @return
     */
    @Select("SELECT substring(staff_num,3,6) as staff_num from staff where staff_num=(select staff_num from staff order by staff_num desc limit 1)")
    Map getStaffnum();

    //修改员工信息
    @Insert("update  staff set staff_num=#{staff_num},staff_name=#{staff_name},staff_phone=#{staff_phone},staff_sex=#{staff_sex},staff_password=#{staff_password},staff_card=#{staff_card}\n" +
            "where staff_id=#{staff_id}")
    int updateStaff(Map map);


    /**
     * 角色
     * @return
     */
    @Insert("<script> insert into staff_role(sid,rid) values\n" +
            "\n" +
            "    <foreach collection=\"list\" item=\"item\" separator=\",\">\n" +
            "        (#{item.sid},#{item.rid})\n" +
            "    </foreach></script>")
    int updateRoleStaff(List<StaffRole> list);


    /**
     * 修改前删除角色中间表
     * @param staffId
     * @return
     */
    @Delete("delete from staff_role where sid=#{staffId}")
    int deleteRoleStaff(String staffId);

}
