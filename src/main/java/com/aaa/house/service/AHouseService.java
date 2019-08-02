package com.aaa.house.service;

import com.aaa.house.entity.HouseImg;
import com.aaa.house.entity.HouseLaIm;
import com.aaa.house.entity.HouseLable;
import com.aaa.house.entity.HouseUser;
import org.apache.ibatis.annotations.Select;

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
    List<HouseUser> getHouseUserAll(Map map);

    /**
     * 查询所有未联系的房 全部
     * @param map
     * @return
     */
    int getHouseUserquall(Map map);
    /**
     * 经济人联系房源
     * @return
     */
    int upHouseUser(Map map);

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
    List<HouseLaIm> getHouse();

    /**
     * 添加房屋
     * @param map
     * @return
     */
    int setHouse(Map map);

    /**
     * 添加房屋标签表
     * @param houseLables
     * @return
     */
    int setHouseLable(List<HouseLable> houseLables);

    /**
     * 添加房屋组图
     * @param list
     * @return
     */
    int setHouseImg(List<HouseImg> list);

}
