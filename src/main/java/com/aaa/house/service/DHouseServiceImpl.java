package com.aaa.house.service;/**
 * @Author: GZB
 * @Description:
 * @Date:Created in 10:20 2019/8/2
 * @Modified By:
 */

import com.aaa.house.dao.DHouseMapper;
import com.aaa.house.entity.HouseLaIm;
import com.aaa.house.utils.CusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DHouseServiceImpl
 * @Author 龚志博
 * @Date 2019/8/2 10:20
 * @Version 1.0
 */
@Service
public class DHouseServiceImpl implements DHouseService {
  @Autowired
  private DHouseMapper dHouseMapper;

  @Override
  public int updateHouse(HouseLaIm houseLaIm) {

    return dHouseMapper.updateHouse(houseLaIm);
  }

  @Override
  public List<Map> getHouse1(Map map) {
    return dHouseMapper.getHouse1(map);
  }

  @Override
  public int upAudit1(Map map) {
    String house_ditstaff = CusUtil.getStaff().getStaff_num();
    map.put("house_ditstaff",house_ditstaff);
    return dHouseMapper.upAudit1(map);
  }

  @Override
  public int queryPageCount(Map map) {
    return dHouseMapper.queryPageCount(map);
  }

  @Override
    public List<Map> queryCode2() {
        return dHouseMapper.queryCode2();
    }
}
