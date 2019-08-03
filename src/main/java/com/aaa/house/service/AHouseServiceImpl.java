package com.aaa.house.service;

import com.aaa.house.dao.AHouseMapping;
import com.aaa.house.entity.*;
import com.aaa.house.utils.CusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * FileName: AHouseServiceImpl
 * Author:   曹康
 * Date:     2019/7/29 22:06
 * Description: 房屋
 */
@Service
public class AHouseServiceImpl implements AHouseService{

    @Autowired
    private AHouseMapping aHouseMapping;

    /**
     * 查询客户发布未联系的房屋
     * @return
     */
    @Override
    public List<HouseUser> getHouseUserAll(Map map) {
        return aHouseMapping.getHouseUserAll(map);
    }

    /**
     * 查询客户发布未联系的房屋数量
     * @param map
     * @return
     */
    @Override
    public int getHouseUserquall(Map map) {
        return aHouseMapping.getHouseUserquall(map);
    }


    /**
     * 联系房源
     * @return
     */
    @Override
    public int upHouseUser(Map map) {
        //获取当前登陆的经济人工号
        Staff staff = CusUtil.getStaff();
        map.put("uh_staff",staff.getStaff_num());
        return aHouseMapping.upHouseUser(map);
    }

    /**
     * 查询当前经济人联系的房源
     * @return
     */
    @Override
    public  List<Map>  getStaffUser() {
        String uh_staff=CusUtil.getStaff().getStaff_num();
        System.out.println(uh_staff);
        return aHouseMapping.getStaffUser(uh_staff);
    }

    /**
     * 查询当前房东的房源
     * @return
     */
    @Override
    public List<HouseUser> getUserHouse(Map map) {
        return aHouseMapping.getUserHouse(map);
    }

    /**
     * 查询所有房屋
     * @return
     */
    @Override
    public List<Map> getHouse(Map map) {
        return aHouseMapping.getHouse(map);
    }

    /**
     * 添加房屋
     * @param map
     * @return
     */
    @Override
    public int setHouse(Map map) {
        return aHouseMapping.setHouse(map);
    }

    /**
     * 添加房屋标签
     * @param houseLables
     * @return
     */
    @Override
    public int setHouseLable(List<HouseLable> houseLables) {
        return aHouseMapping.setHouseLable(houseLables);
    }

    /**
     * 添加房屋组图
     * @param list
     * @return
     */
    @Override
    public int setHouseImg(List<HouseImg> list) {
        return aHouseMapping.setHouseImg(list);
    }
}
