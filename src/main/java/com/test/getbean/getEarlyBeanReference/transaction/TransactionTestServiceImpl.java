package com.test.getbean.getEarlyBeanReference.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * @author 朱伟伟
 * @date 2020-12-03 11:00:48
 * @description
 */
@Service
public class TransactionTestServiceImpl implements TransactionTestService {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    TransactionTestService transactionTestService;

    @Override
//    @Transactional
    public void insert(String name) {
        System.out.println("TransactionTestServiceImpl insert");
        String sql = "insert into transaction.transaction_test(name) values (?);";
        jdbcTemplate.update(sql, name);
        System.out.println(1 / 0);
    }

    @Override
    public List<TransactionTest> getList() {
        System.out.println("TransactionTestServiceImpl getList");
//        jdbcTemplate.update("insert into transaction.transaction_test(name) values ('getList')");
//        System.out.println(1 / 0);
        return jdbcTemplate.query("select id,name from `transaction`.transaction_test", new BeanPropertyRowMapper<>(TransactionTest.class));
    }


    @Autowired
    TransactionTemplate transactionTemplate;

    @Override
    public void transactionTemplate(String name) {
        String result = transactionTemplate.execute(status -> {
            System.out.println("TransactionTestServiceImpl transactionTemplate");
            String sql = "insert into transaction.transaction_test(name) values (?);";
            jdbcTemplate.update(sql, name);
            System.out.println(1 / 0);
            return "transactionTemplate";
        });
    }


    @Autowired
    PlatformTransactionManager platformTransactionManager;
//    @Autowired
//    TransactionManagementConfigurer transactionManagementConfigurer;

    @Override
    public void platformTransactionManager(String name) {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus transaction = platformTransactionManager.getTransaction(transactionDefinition);
//        PlatformTransactionManager platformTransactionManager = (PlatformTransactionManager) transactionManagementConfigurer.annotationDrivenTransactionManager();
//        TransactionStatus transaction = platformTransactionManager.getTransaction(transactionDefinition);
        try {
            System.out.println("TransactionTestServiceImpl transactionTemplate");
            String sql = "insert into transaction.transaction_test(name) values (?);";
            jdbcTemplate.update(sql, name);
            System.out.println(1 / 0);
            platformTransactionManager.commit(transaction);
            System.out.println("platformTransactionManager commit");
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("platformTransactionManager rollback");
            platformTransactionManager.rollback(transaction);
        }
    }

    @Override
    @Transactional(transactionManager = "secondTransactionManager")
    public void insertOne(String name) {
        System.out.println("TransactionTestServiceImpl insertOne");
        String sql = "insert into transaction.transaction_test(name) values (?);";
        jdbcTemplate.update(sql, name);

//        insertTwo("insertTwo");

//        TransactionTestService transactionTestService = (TransactionTestService) AopContext.currentProxy();
//        transactionTestService.insertTwo("insertTwo");

        transactionTestService.insertTwo("insertTwo");

        //Global transaction is marked as rollback-only but transactional code requested commit
        //Transaction rolled back because it has been marked as rollback-only
//        try {
//            transactionTestService.insertTwo("insertTwo");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        System.out.println(1 / 0);
    }

    @Override
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Transactional
    public void insertTwo(String name) {
        System.out.println("TransactionTestServiceImpl insertTwo");
        String sql = "insert into transaction.transaction_test(name) values (?);";
        jdbcTemplate.update(sql, name);
        System.out.println(1 / 0);
    }
}
