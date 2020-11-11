package homework4_1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 */
public class Homework032 {

    static volatile int result = 0;

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        
        long start=System.currentTimeMillis();
        Semaphore semaphore = new Semaphore(1);
        semaphore.acquire();
        // 在这里创建一个线程或线程池，
        new Thread(() -> {
            result = sum();
            semaphore.release();
        }).start();

        semaphore.acquire();

        System.out.println("异步计算结果为："+result);
         
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
    
    private static int sum() {
        return fibo(36);
    }
    
    private static int fibo(int a) {
        if ( a < 2) 
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
