package com.example.getbean.getEarlyBeanReference.transaction.propagation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 朱伟伟
 * @date 2020-12-04 09:35:44
 * @description
 */
@Service
public class TwoServiceImpl implements TwoService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insert(String name) {
        System.out.println("TwoServiceImpl insert");
        String sql = "insert into transaction.transaction_test(name) values (?);";
        jdbcTemplate.update(sql, name);
        System.out.println(1 / 0);
    }
}
