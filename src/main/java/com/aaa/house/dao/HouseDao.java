package com.aaa.house.dao;

import com.aaa.house.entity.House;
import com.aaa.house.entity.Staff;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;

/**
 * fileName:HouseDao
 * description:
 * author:guoxiaoxuan
 * createTime:2019/7/26 21:47
 * versoin:1.0.0
 */
@Repository
public interface HouseDao {
    /**
     * 查询房屋信息
     * @param map
     * @return
     */
    List<House> queryHouseAll(Map map);

    /**
     * 查询总数
     * @param map
     * @return
     */
    Integer queryHousePageCount(Map map);

    /**
     * 根据id查询标签
     * @param houseId
     * @return
     */
    List<String> selectLable(Integer houseId);

    /**
     * 查询房屋布局
     * @return
     */
    List<Map> selectLayout();

    /**
     * 根据id查询房屋图片
     * @param houseId
     * @return
     */
    List<String> selectImgs(Integer houseId);

    /**
     * 根据经纪人id查询经纪人信息
     * @param houseStaff
     * @return
     */
    Staff selectStaff(String houseStaff);

    /**
     * 根据房屋id获取房屋信息
     * @param map
     * @return
     */
    House houseDetail(Map map);

}
