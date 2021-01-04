package com.yoshino.homework1101.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 分布式锁
 *
 * @author wangxin
 **/
public class RedisLock {

    private static class Singleton {
        public static RedisLock instance = new RedisLock();
    }

    public static RedisLock getInstance() {
        return Singleton.instance;
    }


    public boolean lock(String key, long milliseconds) {
        while (!lockInner(key, milliseconds)) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private boolean lockInner(String key, long milliseconds) {
        try (Jedis jedis = JedisHolder.getResource()) {
            return "OK".equals(jedis.set(key, "1", "NX", "PX", milliseconds));
        }
    }

    public boolean release(String key) {
        String luaScript = "if(redis.call('exists', KEYS[1]) == 1) then " +
            "return redis.call('del', KEYS[1]) " +
            "else " +
            "return 0 " +
            "end";
        try (Jedis jedis = JedisHolder.getResource()) {
            return "1".equals(jedis.eval(luaScript, 1, key));
        }
    }

}
