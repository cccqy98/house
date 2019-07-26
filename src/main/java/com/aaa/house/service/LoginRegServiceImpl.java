package com.aaa.house.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Classname：LoginRegServiceImpl
 * @author: L_Fly
 * @Date: 2019/7/26  Time：11:21
 * @Version 1.0.0
 */
@Service
public class LoginRegServiceImpl implements LoginRegService {
    @Override
    public List<Map> query() {
        System.out.println(1111);
        return null;
    }
}
