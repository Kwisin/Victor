package com.example.demo.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {
    @Bean
    public TestConfigurationBean getTestConfiguration() {
        TestConfigurationBean testConfiguration = new TestConfigurationBean();
        testConfiguration.setTestConfigurationBeanName(System.getProperty("custom.name"));
        return testConfiguration;
    }
}


