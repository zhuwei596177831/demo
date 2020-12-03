package com.example.getbean.getEarlyBeanReference;

import com.example.getbean.getEarlyBeanReference.aop.TestJdkProxy;
import com.example.getbean.getEarlyBeanReference.transaction.TransactionTestService;
import com.example.getbean.getEarlyBeanReference.transaction.TransactionTestServiceImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2020-11-26 17:16:02
 * @description {@link EnableAutoConfiguration} 默认：proxyTargetClass = true 开启cglib代理
 * @see org.springframework.boot.autoconfigure.aop.AopAutoConfiguration
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackageClasses = {GetEarlyBeanReferenceConfig.class})
@EnableAspectJAutoProxy(exposeProxy = true)
//@EnableAutoConfiguration
@PropertySource(value = {"classpath:recources.properties"})
public class GetEarlyBeanReferenceConfig {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(GetEarlyBeanReferenceConfig.class);
//        applicationContext.getBean(EarlyBeanReferenceBeanA.class).test("zww");
//        applicationContext.getBean(TestJdkProxy.class).test("zww");
//        applicationContext.getBean(PrototypeEarlyBean.class);
//        applicationContext.getBean(RequestEarlyBean.class);
        applicationContext.getBean(TransactionTestService.class).insert("insert");
//        applicationContext.getBean(TransactionTestService.class).transactionTemplate("transactionTemplate");
//        applicationContext.getBean(TransactionTestService.class).platformTransactionManager("platformTransactionManager");
//        applicationContext.getBean(TransactionTestService.class).getList().forEach(System.out::println);
        applicationContext.close();
    }

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public InitMethodBean initMethodBean() {
        return new InitMethodBean();
    }


}
