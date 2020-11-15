package com.yoshino.lesson1.problem2.answer2;

import com.yoshino.lesson1.bean.Klass;
import com.yoshino.lesson1.bean.School;
import com.yoshino.lesson1.bean.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Answer2 {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
            "com/yoshino/lesson1/problem2/answer2");
        System.out.println(applicationContext.getBean("configStudent", Student.class));
        System.out.println(applicationContext.getBean("school", School.class));
        System.out.println(applicationContext.getBeansOfType(Student.class));
        System.out.println(applicationContext.getBeansOfType(School.class));
        System.out.println(applicationContext.getBeansOfType(Klass.class));
    }
}
