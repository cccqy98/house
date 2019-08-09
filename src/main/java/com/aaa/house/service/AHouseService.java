package com.aaa.house.service;

import com.aaa.house.entity.HouseImg;
import com.aaa.house.entity.HouseLaIm;
import com.aaa.house.entity.HouseLable;
import com.aaa.house.entity.HouseUser;
import com.aaa.house.utils.ResultUtil;

import java.util.List;
import java.util.Map;

/**
 * 房屋
 */
public interface AHouseService {
    /**
     * 查询所有未联系的房
     * @return
     */
    Map getHouseUserAll(Map map);


    /**
     * 经济人联系房源
     * @return
     */
    ResultUtil upHouseUser(Map map);

    /**
     * 查询当前经济人联系的房源
     * @return
     */
    List<Map>  getStaffUser();

    /**
     * 查询当前房东的房源
     * @return
     */
    List<HouseUser> getUserHouse(Map map);

    /**
     * 查询所有房屋
     * @return
     */
    Map getHouse(Map map);

    /**
     * 查询数量
     * @return
     */
    int getHouseNum(Map map);

    /**
     * 房屋修改
     * @return
     */
    ResultUtil UpdaHouse(Map map);

    /**
     * 我的房屋修改
     * @return
     */
    ResultUtil updaMyHouse(Map map);



    /**
     * 房屋假删除
     * @return
     */
    ResultUtil updateHouseDelete(Map map);



    /**
     * 添加房屋
     * @param map
     * @return
     */
    ResultUtil setHouse(Map map);




    /**
     * 经济人查询我的房屋
     * @param map
     * @return
     */
    List<HouseLaIm> getMyHouse(Map map);

    /**
     * 经级人房屋数量
     * @param map
     * @return
     */
    int getMyhouseNum(Map map);

    /**
     * 房屋下架
     * @param map
     * @return
     */
    ResultUtil upMyHouseXiajia(Map map);



    /**
     * 房屋发布
     * @return
     */
    ResultUtil upMYHouse(Map map);



    /**
     * 查询我的租客
     * @param map
     * @return
     */
    Map getpact(Map map);



    /**
     * 预约人数+信息+分页=房源出租
     * @return
     */
    List<HouseLaIm> getLookMyHouse(Map map);

    /**
     * 获取我的以发布房屋的数量
     * @param map
     * @return
     */
    int getLookMyHouseNum(Map map);

    /**
     * 获取该房屋的预约信息获取该房屋的预约信息
     * @param map
     * @return
     */
    List<Map> getUserLookHouse(Map map);

    /**
     * 修改允许看房
     * @param map
     * @return
     */
    ResultUtil updalookHouse(Map map);

    /**
     * 添加看房时间和状态
     * @param map
     *@return
     */
    ResultUtil upLookHousestate(Map map);

    /**
     * 删除无意租的看房用户
     * @param map
     * @return
     */
    ResultUtil deLookHouse(Map map);

    /**
     * 合同添加时间
     * @param map
     * @return
     */
    ResultUtil upPact(Map map);

}
