package com.aaa.house.dao;

import com.aaa.house.entity.BHouseUser;
import com.aaa.house.entity.Code;
import com.aaa.house.entity.HouseUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BCodeDao {
    /*从字典表code中获取类型市级和街道*/
    public List<Code> showAddress(@Param("type") String type);

    /*从字典表code中获取id*/
    public Code showAddres(@Param("id") String id);

    //	public Code showNumber(@Param("code_number") String cnumber);
    /*添加到house_user*/
    public int addUser(BHouseUser user);

    int addUser(HouseUser user);

    @Select("select code_number,code_state FROM code where code_type=11300")
    Map query();

    @Select("select code_number,code_state FROM code where code_type=11300")
    Map query1();
}
