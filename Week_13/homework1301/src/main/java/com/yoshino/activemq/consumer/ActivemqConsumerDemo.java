package com.yoshino.activemq.consumer;

import com.yoshino.activemq.constant.MqQueueConstant;
import com.yoshino.activemq.constant.MqTopicConstant;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * 消息消费者
 *
 * @author wangxin
 * 2021/1/9 00:40
 * @since
 **/
public class ActivemqConsumerDemo {

    final AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws JMSException {
        testMessageListener();

//        testPull();
    }

    private static void testMessageListener() throws JMSException {
        ActivemqConsumerDemo demo = new ActivemqConsumerDemo();
        demo.listener(new ActiveMQTopic(MqTopicConstant.TEST_TOPIC));
        demo.listener(new ActiveMQQueue(MqQueueConstant.TEST_QUEUE));
    }

    private static void testReceiveMessage() throws JMSException {
        ActivemqConsumerDemo demo = new ActivemqConsumerDemo();
        demo.receive(new ActiveMQTopic(MqTopicConstant.TEST_TOPIC));
        demo.receive(new ActiveMQQueue(MqQueueConstant.TEST_QUEUE));
    }

    /**
     * 测试监听消息
     * @param destination
     * @throws JMSException
     */
    public void listener(Destination destination) throws JMSException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Consumer<MessageConsumer> messageConsumerConsumer = consumer -> {
            // 设置mq消费监听器
            try {
                consumer.setMessageListener(message -> {
                    String out = count.incrementAndGet() + " => receive from " + destination.getClass() + ": " + message;
                    System.out.println(out);
                });
                // 两秒超时
                Thread.sleep(20000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                countDownLatch.countDown();
            }
        };
        consume(destination, messageConsumerConsumer, countDownLatch);
    }

    /**
     * 测试主动拉区消息
     * @param destination
     * @throws JMSException
     */
    public void receive(Destination destination) throws JMSException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Consumer<MessageConsumer> messageConsumerConsumer = consumer -> {
            // 超时计数，如果超过5秒没有收到消息则结束
            final AtomicInteger failCount = new AtomicInteger(0);
            try {
                do {
                    Optional<Message> messageOptional = Optional.ofNullable(consumer.receive(1000));
                    if (messageOptional.isPresent()) {
                        String out = count.incrementAndGet() + " => receive from " + destination.getClass()
                            + ": " + messageOptional.get();
                        System.out.println(out);
                    } else {
                        failCount.incrementAndGet();
                    }
                } while (failCount.get() <= 5);
            } catch (JMSException e) {
                throw new RuntimeException(e);
            } finally {
                countDownLatch.countDown();
            }
        };

        consume(destination, messageConsumerConsumer, countDownLatch);
    }

    public void consume(Destination destination, Consumer<MessageConsumer> action, CountDownLatch countDownLatch) throws JMSException{
        ActiveMQConnection conn = null;
        Session session = null;
        try {
            // 创建链接和会话
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
            conn = (ActiveMQConnection) factory.createConnection();
            conn.start();
            // 不使用事务及自动确认模式
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // 创建消费者
            MessageConsumer consumer = session.createConsumer(destination);
            action.accept(consumer);

            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

}
