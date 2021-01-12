package com.yoshino.kafka.publish;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
@Slf4j
public class PublisherApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PublisherApplication.class, args);
    }

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @Override
    public void run(String... args) throws Exception {
        log.info(String.valueOf(kafkaTemplate));
        for (int i = 0; i < 1000; i++) {
            kafkaTemplate.send("topic_test", "msg:" + i);
            kafkaTemplate.flush();
        }
    }

    @KafkaListener(id = "webGroup", topics = "topic_test")
    public void listen(String msg) {
        log.info("消费消息：" + msg);
    }
}
