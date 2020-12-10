package com.example.getbean.getEarlyBeanReference.transaction.propagation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 朱伟伟
 * @date 2020-12-04 09:35:44
 * @description
 */
@Service
public class OneServiceImpl implements OneService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    TwoService twoService;

    @Override
    @Transactional
    @Async
    public void insert(String name) {
        System.out.println("OneServiceImpl insert");
        String sql = "insert into transaction.transaction_test(name) values (?);";
        jdbcTemplate.update(sql, name);
        twoService.insert("two");
//        System.out.println(1 / 0);
    }
}
