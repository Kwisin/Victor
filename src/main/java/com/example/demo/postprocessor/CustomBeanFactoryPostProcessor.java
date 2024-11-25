package com.example.demo.postprocessor;

import com.example.demo.service.TestComponentBean;
import com.example.demo.service.TestDemo;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.PriorityOrdered;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor, PriorityOrdered {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        TestComponentBean testComponentBean = beanFactory.getBean("TestComponentBean", TestComponentBean.class);
        TestDemo testDemo = beanFactory.getBean("TestDemo", TestDemo.class);
        System.out.println();
    }


    @Override
    public int getOrder() {
        return 100;
    }
}
