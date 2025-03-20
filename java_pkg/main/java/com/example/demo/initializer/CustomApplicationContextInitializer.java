package com.example.demo.initializer;

import com.example.demo.postprocessor.CustomBeanDefinitionRegistryPostProcessor;
import com.example.demo.postprocessor.CustomBeanFactoryPostProcessor;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class CustomApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        applicationContext.addBeanFactoryPostProcessor(new CustomBeanFactoryPostProcessor());
        applicationContext.addBeanFactoryPostProcessor(new CustomBeanDefinitionRegistryPostProcessor());
    }
}
