package com.yoshino.homework0702;

import com.yoshino.homework0702.v1.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@SpringBootApplication
public class DataSourceV1Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DataSourceV1Application.class, args);
    }

    @Autowired
    protected DemoService demoService;

    @Override
    public void run(String... args) throws Exception {
        demoService.select();
        demoService.write();
        log.info("\n\n\n\n");
    }
}
