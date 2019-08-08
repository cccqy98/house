package com.aaa.house.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * fileName:BulletinDao
 * description:
 * author:guoxiaoxuan
 * createTime:2019/8/7 15:55
 * versoin:1.0.0
 */
@Repository
public interface BulletinDao {
    /**
     * 查询公告栏内容
     * @param map
     * @return
     */
    List<Map> selectBulletin(Map map);

    /**
     * 查询公告数量
     * @param map
     * @return
     */
    Integer selectBulletinCount(Map map);

    /**
     * 添加公告
     * @param map
     * @return
     */
    Integer insertBulletin(Map map);
    /**
     * 删除公告
     * @param bid
     * @return
     */
    Integer deleteBullById(Integer bid);
}
