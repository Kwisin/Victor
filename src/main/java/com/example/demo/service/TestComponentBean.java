package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class TestComponentBean {
    private String TestComponentBeanName = "TestComponentBean";

    public String getName (){
        return this.TestComponentBeanName;
    }
}
