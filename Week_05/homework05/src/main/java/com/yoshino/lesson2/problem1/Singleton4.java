package com.yoshino.lesson2.problem1;

/**
 * 饱汉式加载
 */
public class Singleton4 {

    private static class SingletonHolder {
        private final static Singleton4 INSTANCE = new Singleton4();
    }

    private Singleton4() {}

    public static Singleton4 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
