package com.yoshino.homework05_2_3use;

import com.yoshino.homework05_2_3.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Homework0523useApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Homework0523useApplication.class, args);
        System.out.println(context.getBean(Student.class));
        System.out.println(context.getBean(Klass.class));
        System.out.println(context.getBean(School.class));
    }

}
