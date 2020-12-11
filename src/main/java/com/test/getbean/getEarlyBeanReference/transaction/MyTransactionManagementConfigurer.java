package com.test.getbean.getEarlyBeanReference.transaction;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * @author 朱伟伟
 * @date 2020-12-10 16:41:27
 * @description
 */
public class MyTransactionManagementConfigurer implements TransactionManagementConfigurer {
    private DataSource dataSource;

    MyTransactionManagementConfigurer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public TransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
