package com.yoshino.lesson2.problem1;

/**
 * 双重检查
 */
public class Singleton1 {

    private static volatile Singleton1 instance;

    private String name;

    private Singleton1(String name) {
        this.name = name;
    }

    public static Singleton1 getInstance() {
        if (instance == null) {
            synchronized(Singleton1.class) {
                if (instance == null) {
                    instance = new Singleton1("双重检查");
                }
            }
        }
        return instance;
    }
}
