package com.yoshino.homework01;

import com.yoshino.homework01.demo.DemoService;
import com.yoshino.homework01.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Homework01Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Homework01Application.class, args);
    }


    @Autowired
    private DemoService demoService;

    @Override
    public void run(String... args) throws Exception {
        demoService.curdSingle();
    }
}
