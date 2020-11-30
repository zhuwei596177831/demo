package com.example.demo.generic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 朱伟伟
 * @date 2020-09-02 09:41:17
 * @description
 */
@Component
public class DDD {
    @Autowired
    private BaseDao<User> userBaseDao;
    @Autowired
    private BaseDao<Student> studentBaseDao;
}
