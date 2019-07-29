package com.aaa.house.service;

import com.aaa.house.dao.DMaintainMapping;
import com.aaa.house.entity.Maintain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName DMaintainServiceImpl
 * @Author 龚志博
 * @Date 2019/7/27 10:37
 * @Version 1.0
 */
@Service
public class DMaintainServiceImpl implements DMaintainService{
@Autowired
private DMaintainMapping maintainMapping;
    @Override
    public List<Maintain> queryAll() {
        return maintainMapping.queryAll();
    }

    @Override
    public int AddMaintain(Maintain maintain) {
        return maintainMapping.AddMaintain(maintain);
    }

    @Override
    public List<Map> queryStatic(Map map) {
        return maintainMapping.queryStatic(map);
    }

    @Override
    public List<Map> queryAudit(Map map) {
        return maintainMapping.queryAudit(map);
    }

    @Override
    public int delete() {
        return maintainMapping.delete();
    }

    @Override
    public int updateMa(Maintain maintain) {
        return maintainMapping.updateMa(maintain);
    }
}
