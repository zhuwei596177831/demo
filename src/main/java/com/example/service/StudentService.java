package com.example.service;

import com.example.generic.Student;
import com.example.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 朱伟伟
 * @date 2021-03-06 21:19:03
 * @description
 */
@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Transactional
    public Integer updateById() {
        Student student = new Student();
        student.setId("1343074721642283009");
        student.setName("朱伟伟哈哈哈");
        int i = studentMapper.updateById(student);
        return i;
    }
}
