package com.aaa.house.service;/**
 * @Author: GZB
 * @Description:
 * @Date:Created in 9:06 2019/8/6
 * @Modified By:
 */

import com.aaa.house.dao.EchartsMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName EchartsServiceImpl
 * @Author 龚志博
 * @Date 2019/8/6 9:06
 * @Version 1.0
 */
@Service
public class EchartsServiceImpl implements EchartsService {
    @Autowired
    private EchartsMapping mapping;
    @Override
    public List<Map> queryDPact(Map map) {
        return mapping.queryDPact(map);
    }
}
