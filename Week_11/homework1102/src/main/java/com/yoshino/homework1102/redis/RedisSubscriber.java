package com.yoshino.homework1102.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 * redis订阅者
 *
 * @author wangxin
 * 2021/1/4 22:52
 * @since
 **/
public class RedisSubscriber extends JedisPubSub {

    private String name;

    public RedisSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onMessage(String channel, String message) {
        System.out.println(name + ":获取订阅信息：" + message);
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println(name + ":subscribe channel = " + channel);
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println(name + ":unSubscribe channel = " + channel);
    }
}
