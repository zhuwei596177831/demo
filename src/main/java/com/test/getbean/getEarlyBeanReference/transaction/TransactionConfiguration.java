package com.test.getbean.getEarlyBeanReference.transaction;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration;
import org.springframework.transaction.interceptor.*;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author 朱伟伟
 * @date 2020-12-03 10:44:05
 * @description
 * @see org.springframework.transaction.annotation.TransactionManagementConfigurationSelector
 * @see AutoProxyRegistrar
 * @see ProxyTransactionManagementConfiguration
 * @see BeanFactoryTransactionAttributeSourceAdvisor
 * @see AnnotationTransactionAttributeSource
 * @see TransactionInterceptor
 * @see org.springframework.transaction.annotation.SpringTransactionAnnotationParser
 */
@Configuration(proxyBeanMethods = false)
@EnableTransactionManagement
public class TransactionConfiguration {

    @Bean
    public DataSource dataSource() throws SQLException {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setUrl("jdbc:mysql://127.0.0.1:3306");
        mysqlDataSource.setServerTimezone("GMT+8");
        mysqlDataSource.setDatabaseName("transaction");
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("root");
        return mysqlDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * @param dataSource:
     * @author: 朱伟伟
     * @date: 2020-12-10 16:39
     * @description: 1、直接添加transactionManager bean
     * 2、添加自定义的TransactionManagementConfigurer(only one)
     **/
    @Bean
    @Primary
    public PlatformTransactionManager primaryTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public PlatformTransactionManager secondTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


//    @Bean
//    public TransactionManagementConfigurer transactionManagementConfigurer(DataSource dataSource) {
//        return new MyTransactionManagementConfigurer(dataSource);
//    }


    /**
     * @param transactionManager:
     * @author: 朱伟伟
     * @date: 2020-12-03 10:51
     * @description: 声明式事务：
     * 使用拦截器（AOP方式的直接使用)
     **/
    // 配置一个事务的拦截器（相当于一个AOP的`环绕通知`）
//    @Bean
//    public TransactionInterceptor transactionInterceptor(TransactionManager transactionManager) {
//        Map<String, TransactionAttribute> methodNamesMap = new HashMap<>();
//        RuleBasedTransactionAttribute ruleBasedTransactionAttribute = new RuleBasedTransactionAttribute();
//        List<RollbackRuleAttribute> rollbackRules = new ArrayList<>(2);
//        rollbackRules.add(RollbackRuleAttribute.ROLLBACK_ON_RUNTIME_EXCEPTIONS);
//        rollbackRules.add(new RollbackRuleAttribute(Error.class));
//        ruleBasedTransactionAttribute.setRollbackRules(rollbackRules);
//        ruleBasedTransactionAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//        ruleBasedTransactionAttribute.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
//        methodNamesMap.put("insert*", ruleBasedTransactionAttribute);
//        methodNamesMap.put("add*", ruleBasedTransactionAttribute);
//        methodNamesMap.put("update*", ruleBasedTransactionAttribute);
//        methodNamesMap.put("delete*", ruleBasedTransactionAttribute);
//        DefaultTransactionAttribute readOnly = new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
//        readOnly.setReadOnly(true);
//        methodNamesMap.put("select*", readOnly);
//        methodNamesMap.put("get*", readOnly);
//        methodNamesMap.put("find*", readOnly);
//        NameMatchTransactionAttributeSource transactionAttributeSource = new NameMatchTransactionAttributeSource();
//        transactionAttributeSource.setNameMap(methodNamesMap);
//        return new TransactionInterceptor(transactionManager, transactionAttributeSource);
//    }

    /**
     * @param transactionManager:
     * @author: 朱伟伟
     * @date: 2020-12-03 17:01
     * @description: 注解方式(手动添加TransactionInterceptor)
     * {@link org.springframework.transaction.annotation.Transactional}
     **/
//    @Bean
//    public TransactionInterceptor transactionInterceptor(TransactionManager transactionManager) {
//        return new TransactionInterceptor(transactionManager, new AnnotationTransactionAttributeSource(true));
//    }

    // 配置一个自动代理创建器  决定这个事务拦截器要作用于哪些Bean上
//    @Bean
//    public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
//        BeanNameAutoProxyCreator proxyCreator = new BeanNameAutoProxyCreator();
//        proxyCreator.setBeanNames("*ServiceImpl");
//        // 此处这个BeanName一定要对应上~~~~
//        proxyCreator.setInterceptorNames("transactionInterceptor");
//        return proxyCreator;
//    }

    /**
     * @param transactionManager:
     * @author: 朱伟伟
     * @date: 2020-12-03 11:15
     * @description: 编程式事务：TransactionTemplate
     **/
    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager transactionManager) {
        return new TransactionTemplate(transactionManager);
    }
//    @Bean
//    public TransactionTemplate transactionTemplate(TransactionManagementConfigurer transactionManagementConfigurer) {
//        return new TransactionTemplate((PlatformTransactionManager) transactionManagementConfigurer.annotationDrivenTransactionManager());
//    }

    /**
     * @param null:
     * @author: 朱伟伟
     * @date: 2020-12-03 11:31
     * @description: 编程式事务: PlatformTransactionManager(业务代码手动获取设置)
     **/


}
