package com.yoshino.homework1102.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis发布者
 *
 * @author wangxin
 * 2021/1/4 22:52
 * @since
 **/
public class RedisPublisher {

    private String channel;

    private JedisPool jedisPool;

    public RedisPublisher(String channel, JedisPool jedisPool) {
        this.channel = channel;
        this.jedisPool = jedisPool;
    }

    public void publish(String msg) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.publish(channel, msg);
        }
    }


}
