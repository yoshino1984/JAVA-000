package com.yoshino.lesson2.problem1;

/**
 * 饿汉式单例模式
 */
public class Singleton2 {

    private static final Singleton2 instance = new Singleton2();

    private Singleton2() {}

    public static Singleton2 getInstance() {
        return instance;
    }
}
