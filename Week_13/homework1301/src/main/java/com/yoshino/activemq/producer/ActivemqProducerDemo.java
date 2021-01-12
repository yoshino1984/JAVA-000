package com.yoshino.activemq.producer;

import com.yoshino.activemq.constant.MqQueueConstant;
import com.yoshino.activemq.constant.MqTopicConstant;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者
 *
 * @author wangxin
 * 2021/1/9 00:40
 * @since
 **/
public class ActivemqProducerDemo {
    public static void main(String[] args) {
        testDestination(new ActiveMQTopic(MqTopicConstant.TEST_TOPIC));
        testDestination(new ActiveMQQueue(MqQueueConstant.TEST_QUEUE));
    }


    public static void testDestination(Destination destination) {
        try {
            // 创建连接和会话
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
            ActiveMQConnection conn = (ActiveMQConnection) factory.createConnection();
            conn.start();
            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // 创建生产者，生产100个消息
            MessageProducer producer = session.createProducer(destination);
            int index = 0;
            while (index++ < 100) {
                TextMessage message = session.createTextMessage(index + " message.");
                producer.send(message);
            }

            Thread.sleep(2000);
            session.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
