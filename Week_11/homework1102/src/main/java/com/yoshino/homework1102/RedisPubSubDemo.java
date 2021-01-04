package com.yoshino.homework1102;

import com.alibaba.fastjson.JSON;
import com.yoshino.homework1102.bean.Order;
import com.yoshino.homework1102.redis.RedisPublisher;
import com.yoshino.homework1102.redis.RedisSubscriber;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.math.BigDecimal;
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

        for (int i = 0; i < 2; i++) {
            String subscribeName = "subscribe-" + i;
            Runnable runnable = () -> {
                try (Jedis jedis = jedisPool.getResource()){
                    RedisSubscriber.subscribe(channelName, jedis, subscribeName);
                    System.out.println("after subscribe");
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
        executor.shutdownNow();
    }

}
