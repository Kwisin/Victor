package com.example.demo.service;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("TestDemo")
public class TestDemo {

    @Resource
    public TestComponentBean testComponentBean;

    public String getName () {
        return this.testComponentBean.getName();
    }
}
