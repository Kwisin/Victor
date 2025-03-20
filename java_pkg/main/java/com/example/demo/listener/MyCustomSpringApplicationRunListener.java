package com.example.demo.listener;

import com.example.demo.service.TestComponentBean;
import com.example.demo.service.TestDemo;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.time.Duration;

public class MyCustomSpringApplicationRunListener implements SpringApplicationRunListener {

    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        System.out.println("MyCustomSpringApplicationRunListener starting");
    }

    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext,
                                    ConfigurableEnvironment environment) {
        System.out.println("MyCustomSpringApplicationRunListener environmentPrepared");
    }

    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        TestComponentBean testComponentBean = context.getBean("TestComponentBean", TestComponentBean.class);
        TestDemo testDemo = context.getBean("TestDemo", TestDemo.class);
        String name = testDemo.getName();
        System.out.println();
    }

}
