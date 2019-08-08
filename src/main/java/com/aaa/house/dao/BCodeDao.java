package com.aaa.house.dao;


import org.apache.ibatis.annotations.Insert;

import org.springframework.stereotype.Repository;


import java.util.Map;

@Repository
public interface BCodeDao {

   /* *//*从字典表code中获取类型市级和街道*//*
    public List<Code> showAddress(@Param("type") String type);
    *//*从字典表code中获取id*//*
    public Code showAddres(@Param("id") String id);
    //	public Code showNumber(@Param("code_number") String cnumber);
    *//*添加到house_user*//*
    public int addUser(BHouseUser user);

    int addUser(HouseUser user);*/

    /**
     * 添加到house_usera
     * @param map
     * @return
     */
    @Insert("insert into house_user (uh_urban,uh_street,uh_district,uh_rent,uh_name,uh_phone,uh_useid) " +
            " values(#{uh_urban},#{uh_street},#{uh_district},#{uh_rent},#{uh_name},#{uh_phone},#{uh_useid})")
     int addUser(Map map);



}
