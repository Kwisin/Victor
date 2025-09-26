package main.resources;

import com.example.demo.service.TestComponentBean;
import com.example.demo.service.TestDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages = {"com.example.demo.service"})
@ImportResource(locations = {"classpath:applicationContext.xml"})
public class Demo1Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Demo1Application.class, args);
        TestDemo testDemo = context.getBean("TestDemo", TestDemo.class);
        System.out.println();
    }

}
