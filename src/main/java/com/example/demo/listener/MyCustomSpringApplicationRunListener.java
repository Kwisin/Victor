package com.example.demo.listener;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplicationRunListener;

public class MyCustomSpringApplicationRunListener implements SpringApplicationRunListener {

    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        System.out.println("MyCustomSpringApplicationRunListener starting");
    }

}
