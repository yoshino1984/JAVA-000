package com.yoshino.lesson1.problem1;

import java.lang.reflect.Proxy;

public class DynamicProxyDemo {

    public static void main(String[] args) {
        ITarget org = new Target();

        ITarget targetProxy = (ITarget) Proxy.newProxyInstance(ITarget.class.getClassLoader(),
            new Class[] {ITarget.class},
            (proxy, method, args1) -> {
                System.out.println("dynamic proxy before invoke");
                String result = (String) method.invoke(org, args1);
                System.out.println("dynamic proxy after invoke");
                return result;
            });

        targetProxy.f1();
    }
}
