package com.yoshino.lesson2.problem1;

/**
 * 枚举单例
 */
public enum  Singleton3 {

    INSTANCE("one");

    Singleton3(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
