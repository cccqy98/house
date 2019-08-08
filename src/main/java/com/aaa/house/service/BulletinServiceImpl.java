package com.aaa.house.service;

import com.aaa.house.dao.BulletinDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * fileName:BulletinServiceImpl
 * description:
 * author:guoxiaoxuan
 * createTime:2019/8/7 16:34
 * versoin:1.0.0
 */
@Service
public class BulletinServiceImpl implements BulletinService {

    @Autowired
    private BulletinDao bulletinDao;
    @Override
    /**
     * 查询公告栏内容
     * @param map
     * @return
     */
    public List<Map> selectBulletin(Map map) {
        return bulletinDao.selectBulletin(map);
    }

    @Override
    /**
     * 查询公告数量
     * @param map
     * @return
     */
    public Integer selectBulletinCount(Map map) {
        return bulletinDao.selectBulletinCount(map);
    }

    @Override
    /**
     * 添加公告
     * @param map
     * @return
     */
    public Integer insertBulletin(Map map) {
        return bulletinDao.insertBulletin(map);
    }

    @Override
    /**
     * 删除公告
     * @param bid
     * @return
     */
    public Integer deleteBullById(Integer bid) {
        return bulletinDao.deleteBullById(bid);
    }
}
