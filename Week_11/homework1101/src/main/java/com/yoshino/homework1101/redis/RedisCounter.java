package com.yoshino.homework1101.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 分布式计数器
 *
 * @author wangxin
 * 2021/1/4 22:19
 * @since
 **/
public class RedisCounter {

    private static class Singleton {
        public final static RedisCounter INSTANCE = new RedisCounter();
    }

    public static RedisCounter getInstance() {
        return RedisCounter.Singleton.INSTANCE;
    }

    public long del(String key) {
        try (Jedis jedis = JedisHolder.getResource()) {
            return jedis.del(key);
        }
    }

    public long incrBy(String key, long val) {
        try (Jedis jedis = JedisHolder.getResource()) {
            return jedis.incrBy(key, val);
        }
    }

    public long decrBy(String key, long val) {
        try (Jedis jedis = JedisHolder.getResource()) {
            return jedis.decrBy(key, val);
        }
    }
}
