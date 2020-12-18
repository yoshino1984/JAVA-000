package com.yoshino.account;

import com.yoshino.common.account.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, DataSourceAutoConfiguration.class})
@ImportResource({"classpath:spring-dubbo.xml"})
@MapperScan("com.yoshino.common.account.mapper")
@Slf4j
public class DemoAccount0902Application implements CommandLineRunner, ApplicationContextAware {

    public static void main(String[] args) {
        SpringApplication.run(DemoAccount0902Application.class, args);
        System.out.println("-------success---------");
    }

    @Autowired
    private AccountMapper accountMapper;

    private ApplicationContext applicationContext;


    @Override
    public void run(String... args) throws Exception {
        log.info("account size:" + accountMapper.getCount());
        log.info("account 10000:" + accountMapper.findByUserId("10000"));
        log.info("account 20000:" + accountMapper.findByUserId("20000"));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
