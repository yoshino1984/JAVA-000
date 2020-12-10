package com.yoshino.order;

import com.yoshino.common.order.api.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

import java.math.BigDecimal;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@ImportResource({"classpath:spring-dubbo.xml"})
@MapperScan("com.yoshino.common.order.mapper")
@Slf4j
public class TccOrderApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TccOrderApplication.class, args);
        System.out.println("-------success---------");
    }

    @Autowired
    private OrderService orderService;

    @Override
    public void run(String... args) throws Exception {
        Thread.sleep(3000);
        log.info("\n\n\n\n\n\n\n\n\n\n");
        orderService.orderPay(1, BigDecimal.valueOf(1));
        log.info("\n\n\n\n\n\n\n\n\n\n");
        Thread.sleep(3000);
        orderService.orderPayWithExp(1, BigDecimal.valueOf(1));
    }
}
