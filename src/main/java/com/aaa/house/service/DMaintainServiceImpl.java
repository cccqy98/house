package com.aaa.house.service;

import com.aaa.house.dao.DMaintainMapping;
import com.aaa.house.entity.Maintain;
import com.aaa.house.utils.CusUtil;
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
    public int queryPageCount(Map map) {

        return maintainMapping.queryPageCount(map);
    }


    @Override
    public List<Map> queryStatic(Map map) {

        return maintainMapping.queryStatic(map);
    }

    /**
     * 数量
     * @param map
     * @return
     */
    @Override
    public int queryPageCount1(Map map) {
        return maintainMapping.queryPageCount1(map);
    }

    /**
     * 修改审核
     * @param map
     * @return
     */
    @Override
    public int upAudit(Map map) {
        String ma_staff = CusUtil.getStaff().getStaff_num();
        map.put("ma_staff",ma_staff);
        return maintainMapping.upAudit(map);
    }

    @Override
    public List<Map> queryCode1() {
        return maintainMapping.queryCode1();
    }

    @Override
    public List<Map> queryCode() {
        return maintainMapping.queryCode();
    }

    @Override
    public int upMaintain(Map map) {
        String ma_staff = CusUtil.getStaff().getStaff_num();
        map.put("ma_staff",ma_staff);
        return maintainMapping.upMaintain(map);
    }

    @Override
    public List<Map> queryAudit(Map map) {
        return maintainMapping.queryAudit(map);
    }

    @Override
    public int updateAu(Maintain maintain) {
        return maintainMapping.updateAu(maintain);
    }

    @Override
    public int updateMa(Maintain maintain) {
        return maintainMapping.updateMa(maintain);
    }

}
