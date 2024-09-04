package com.example.demo.config;

import com.example.demo.service.TestXMLBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:custom.properties")
@ComponentScan(basePackages = {"com.example.demo.service", "com.example.demo.service"})
@Import(value = TestXMLBean.class)
public class CustomConfig {
    @Value("custom.name")
    public String name;

}
