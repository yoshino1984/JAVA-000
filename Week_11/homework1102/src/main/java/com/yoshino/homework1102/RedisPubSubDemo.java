package com.yoshino.homework1102;

import com.alibaba.fastjson.JSON;
import com.yoshino.homework1102.bean.Order;
import com.yoshino.homework1102.redis.RedisPublisher;
import com.yoshino.homework1102.redis.RedisSubscriber;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangxin
 * 2021/1/4 22:47
 * @since
 **/
public class RedisPubSubDemo {

    public static void main(String[] args) throws InterruptedException {
        JedisPool jedisPool = new JedisPool();
        String channelName = "orderChannel";
        int threadSize = 2;
        ExecutorService executor = Executors.newFixedThreadPool(threadSize);
        List<RedisSubscriber> subscriberList = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            String subscribeName = "subscribe-" + i;
            RedisSubscriber subscriber = new RedisSubscriber(subscribeName);
            subscriberList.add(subscriber);
            Runnable runnable = () -> {
                try (Jedis jedis = jedisPool.getResource()){
                    jedis.subscribe(subscriber, channelName);
                } catch (Exception e) {
                    System.out.println(String.format("subscribe channel error"));
                }
            };
            executor.submit(runnable);
        }

        RedisPublisher publisher = new RedisPublisher(channelName, jedisPool);
        for (int i = 0; i < 2; i++) {
            Order order = Order.builder()
                .id((long) i)
                .name("订单" + i)
                .orderTime(System.currentTimeMillis())
                .money(BigDecimal.valueOf(1984 * i))
                .status("PAID")
                .build();
            publisher.publish(JSON.toJSONString(order));
        }

        Thread.sleep(2000);
        // 取消订阅
        subscriberList.forEach(JedisPubSub::unsubscribe);
        executor.shutdownNow();
    }

}
