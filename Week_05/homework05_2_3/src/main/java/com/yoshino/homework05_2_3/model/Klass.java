package com.yoshino.homework05_2_3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Klass { 

    @Autowired
    List<Student> students;
    
    public void dong(){
        System.out.println(this.getStudents());
    }
    
}
