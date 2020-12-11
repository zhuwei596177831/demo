package com.test.getbean.getEarlyBeanReference.transaction.propagation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;

import java.util.ArrayList;
import java.util.List;

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
    @Transactional(propagation = Propagation.REQUIRES_NEW, transactionManager = "secondTransactionManager")
    public void insert(String name) {
        System.out.println("TwoServiceImpl insert");
        String sql = "insert into transaction.transaction_test(name) values (?);";
        jdbcTemplate.update(sql, name);
        System.out.println(1 / 0);
    }


    @Autowired
    PlatformTransactionManager transactionManager;

    @Override
    public void manualRollBackInsert(String name) {
        List<RollbackRuleAttribute> rollbackRuleAttributes = new ArrayList<>();
        rollbackRuleAttributes.add(new RollbackRuleAttribute(RuntimeException.class));
        rollbackRuleAttributes.add(new RollbackRuleAttribute(Error.class));
        RuleBasedTransactionAttribute ruleBasedTransactionAttribute = new RuleBasedTransactionAttribute(Propagation.REQUIRES_NEW.value(), rollbackRuleAttributes);
        ruleBasedTransactionAttribute.setIsolationLevel(Isolation.READ_COMMITTED.value());
        TransactionStatus transactionStatus = transactionManager.getTransaction(ruleBasedTransactionAttribute);
        try {
            System.out.println("TwoServiceImpl manualRollBackInsert");
            String sql = "insert into transaction.transaction_test(name) values (?);";
            jdbcTemplate.update(sql, name);
            System.out.println(1 / 0);
            transactionManager.commit(transactionStatus);
        } catch (Throwable e) {
            e.printStackTrace();
            transactionManager.rollback(transactionStatus);
        }
    }
}
