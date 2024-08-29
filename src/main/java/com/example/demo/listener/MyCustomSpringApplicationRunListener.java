package com.example.demo.listener;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.core.env.ConfigurableEnvironment;

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

}
