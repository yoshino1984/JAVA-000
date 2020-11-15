package com.yoshino.homework05_2_3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class School implements ISchool {

    @Autowired
    private Klass class1;
    
    @Resource(name = "student100")
    private Student student100;
    
    @Override
    public void ding(){
    
        System.out.println("Class1 have " + class1.getStudents().size() + " students and one is " + this.student100);
        
    }
    
}
