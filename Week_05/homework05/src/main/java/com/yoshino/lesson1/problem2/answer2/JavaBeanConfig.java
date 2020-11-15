package com.yoshino.lesson1.problem2.answer2;

import com.yoshino.lesson1.bean.Klass;
import com.yoshino.lesson1.bean.School;
import com.yoshino.lesson1.bean.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaBeanConfig {

    @Bean
    public School getSchool() {
        return new School();
    }

    @Bean(name = "school")
    public School getSchool1() {
        return new School();
    }

    @Bean
    public Student getConfigStudent(){
        return new Student();
    }

    @Bean(name = "configStudent")
    public Student getConfigStudent1(){
        return new Student();
    }

    @Bean(name = "student100")
    public Student getStudent100(){
        return new Student();
    }

    @Bean
    public Klass getKlass(){
        return new Klass();
    }
}
