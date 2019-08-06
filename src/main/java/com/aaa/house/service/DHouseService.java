package com.aaa.house.service;


import com.aaa.house.entity.HouseLaIm;

import java.util.List;
import java.util.Map;

/**
 * @Author: GZB
 * @Description:
 * @Date:Created in 10:12 2019/8/2
 * @Modified By:
 */
public interface DHouseService {
    List<Map> getHouse1(Map map);
    /**
     * 数量
     * @return
     */
    int queryPageCount(Map map);
    /**
     * 查询所有审核状态
     * @return
     */
    List<Map> queryCode2();

    /**
     * 审核驳回
     * @return
     */
    int updateHouse(HouseLaIm houseLaIm);
    /**
     * 审核通过
     * @param map
     * @return
     */
    int upAudit1(Map map);
}
