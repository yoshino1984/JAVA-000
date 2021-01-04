package com.yoshino.homework1101.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author wangxin
 * 2021/1/4 22:20
 * @since
 **/
public class JedisHolder {

    private final static JedisPool JEDIS_POOL = new JedisPool();

    public static Jedis getResource() {
        return JEDIS_POOL.getResource();
    }

}
