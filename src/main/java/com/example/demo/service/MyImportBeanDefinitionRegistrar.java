package com.example.demo.service;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry,
                                 BeanNameGenerator importBeanNameGenerator) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(TestConfigurationBean.class);
        registry.registerBeanDefinition("myService", beanDefinition);
    }
}
