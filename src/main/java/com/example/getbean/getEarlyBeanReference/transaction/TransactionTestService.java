package com.example.getbean.getEarlyBeanReference.transaction;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 朱伟伟
 * @date 2020-12-03 15:48:30
 * @description
 */
public interface TransactionTestService {
    @Transactional
    void insert(String name);
    List<TransactionTest> getList();
    void transactionTemplate(String name);
    void platformTransactionManager(String name);
}
