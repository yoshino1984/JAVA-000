package com.yoshino.lesson1.problem2.answer1;

import com.yoshino.lesson1.bean.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Answer1 {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student100 = applicationContext.getBean("student100", Student.class);
        System.out.println(student100);
    }
}
