package com.aaa.house.service;

import com.aaa.house.dao.HouseDao;
import com.aaa.house.entity.House;
import com.aaa.house.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * fileName:HouseServiceImpl
 * description:
 * author:guoxiaoxuan
 * createTime:2019/7/26 22:10
 * versoin:1.0.0
 */
@Service
public class HouseServiceImpl implements HouseService{

    @Autowired
    private HouseDao houseDao;

    @Override
    public List<House> queryHouseAll(Map map) {
        List<House> houses = houseDao.queryHouseAll(map);
        Map<String,Object> maps=new HashMap<>();
        for (House hou : houses) {
            Integer houseId = hou.getHouseId();
            if (houseId!=null){
                List<String> lab = houseDao.selectLable(houseId);
                hou.setHouseLabel(lab);
            }
        }
        return houses;
    }

    @Override
    public Integer queryHousePageCount(Map map) {
        Integer i=houseDao.queryHousePageCount(map);
        System.out.println(i);
        return i;
    }

    @Override
    public List<Map> selectLayout() {
        return houseDao.selectLayout();
    }

    @Override
    public Map<String,Object> housedetail(Map map) {
        House houses=houseDao.houseDetail(map);
        Map<String,Object> maps=new HashMap<>();
        Integer hid=houses.getHouseId();
        List<String> lab = houseDao.selectLable(hid);
        houses.setHouseLabel(lab);
        List<String> img = houseDao.selectImgs(hid);
        houses.setHouseImgs(img);
        String staff = houses.getHouseStaffid();
        Staff sta=houseDao.selectStaff(staff);
        maps.put("obj",houses);
        maps.put("staff",sta);
        return maps;
    }
}
