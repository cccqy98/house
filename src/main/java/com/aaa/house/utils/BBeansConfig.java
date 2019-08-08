package com.aaa.house.utils;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

public class BBeansConfig {
    @Configuration
    @MapperScan(basePackages = {"com.aaa.house.dao"})
    public class BeansConfig {
    }
}
