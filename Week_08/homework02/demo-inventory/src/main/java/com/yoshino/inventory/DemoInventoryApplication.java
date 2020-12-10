package com.yoshino.inventory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@ImportResource({"classpath:spring-dubbo.xml"})
@MapperScan("com.yoshino.common.inventory.mapper")
public class DemoInventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoInventoryApplication.class, args);
        System.out.println("-------success---------");
    }

}
