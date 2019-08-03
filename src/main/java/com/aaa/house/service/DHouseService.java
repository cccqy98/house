package com.aaa.house.service;


import java.util.List;
import java.util.Map;

/**
 * @Author: GZB
 * @Description:
 * @Date:Created in 10:12 2019/8/2
 * @Modified By:
 */
public interface DHouseService {
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
     * 审核通过
     * @param map
     * @return
     */
    int upAudit1(Map map);
}
