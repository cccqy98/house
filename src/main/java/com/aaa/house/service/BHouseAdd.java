package com.aaa.house.service;

import com.aaa.house.dao.BCodeDao;
import com.aaa.house.entity.BHouseUser;
import com.aaa.house.entity.Code;

import com.aaa.house.utils.CusUtil;
import com.aaa.house.utils.ISysConstants;
import com.aaa.house.utils.ResultUtil;
import com.mysql.jdbc.util.ResultSetUtil;
import com.sun.xml.internal.ws.binding.FeatureListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BHouseAdd implements BHouseAddService{

    @Autowired
    private BCodeDao bCodeDao;

    /**
     * 添加到house表
     * @param map
     * @return
     */
    @Override
    public ResultUtil addUser(Map map) {
        map.put("uh_useid",CusUtil.getCusFromSession().getId());
        int addUser=bCodeDao.addUser(map);
        ResultUtil resultUtil=new ResultUtil();
        if (addUser>0){
            resultUtil.setCode(ISysConstants.SUCCESSCODE);
            return resultUtil;
        }
        resultUtil.setCode(ISysConstants.OTHERTIPS);
        return resultUtil;
    }

}
