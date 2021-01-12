package com.yoshino.kafka.subscribe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class SubscriberApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubscriberApplication.class, args);
    }

    @KafkaListener(id = "webGroup2", topics = "topic_test")
    public void listen1(String msg) {
        log.info("消费消息：" + msg);
    }

    @KafkaListener(id = "webGroup3", topics = "topic_test")
    public void listen2(String msg) {
        log.info("消费消息：" + msg);
    }
}
