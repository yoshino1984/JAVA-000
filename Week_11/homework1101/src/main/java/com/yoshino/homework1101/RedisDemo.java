package com.yoshino.homework1101;

import com.yoshino.homework1101.redis.RedisCounter;
import com.yoshino.homework1101.redis.RedisLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author wangxin
 * 2021/1/4 19:57
 * @since
 **/
public class RedisDemo {

    public static void main(String[] args) throws InterruptedException {
        int threadSize = 8;
        ExecutorService executor = Executors.newFixedThreadPool(threadSize);

        String lockKey = "redisKey";
        String stockCountKey = "stockCount";

        // 先清空以重复测试
        RedisCounter.getInstance().del(stockCountKey);
        // 首先增加到800个库存
        int totalStock = 800;
        RedisCounter.getInstance().incrBy(stockCountKey, totalStock);

        // 8个线程，每个线程执行100次减库存
        for (int i = 0; i < threadSize; i++) {
            Runnable decreaseStockTask = () -> IntStream.range(0, 100).forEach(j -> {
                RedisLock.getInstance().lock(lockKey, 10000);
                try {
                    Thread.sleep(10);
                    RedisCounter.getInstance().decrBy(stockCountKey, 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    RedisLock.getInstance().release(lockKey);
                }
            });
            executor.submit(decreaseStockTask);
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
            int count = Math.toIntExact(RedisCounter.getInstance().incrBy(stockCountKey, 0));
            System.out.println(count);
            if (count == 0) {
                System.out.println("扣减库存成功");
                break;
            }
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime) + "ms");

        executor.shutdownNow();
    }

}
