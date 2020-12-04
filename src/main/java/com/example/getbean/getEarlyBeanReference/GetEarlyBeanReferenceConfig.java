package com.example.getbean.getEarlyBeanReference;

import com.example.getbean.getEarlyBeanReference.aop.TestJdkProxy;
import com.example.getbean.getEarlyBeanReference.async.AsyncService;
import com.example.getbean.getEarlyBeanReference.transaction.TransactionTestService;
import com.example.getbean.getEarlyBeanReference.transaction.TransactionTestServiceImpl;
import com.example.getbean.getEarlyBeanReference.transaction.propagation.OneService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(GetEarlyBeanReferenceConfig.class);
//        applicationContext.getBean(EarlyBeanReferenceBeanA.class).test("zww");
//        applicationContext.getBean(TestJdkProxy.class).test("zww");
//        applicationContext.getBean(PrototypeEarlyBean.class);
//        applicationContext.getBean(RequestEarlyBean.class);
//        applicationContext.getBean(TransactionTestService.class).insert("insert");
//        applicationContext.getBean(TransactionTestService.class).transactionTemplate("transactionTemplate");
//        applicationContext.getBean(TransactionTestService.class).platformTransactionManager("platformTransactionManager");
//        applicationContext.getBean(TransactionTestService.class).getList().forEach(System.out::println);
//        applicationContext.getBean(TransactionTestService.class).insertOne("insertOne");
//        applicationContext.getBean(OneService.class).insert("one");
        applicationContext.getBean(AsyncService.class).asyncMethod();
        Future<String> future = applicationContext.getBean(AsyncService.class).futureMethod();
        while (!future.isDone()) {
            System.out.println(future.get());
        }
        applicationContext.close();
    }

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public InitMethodBean initMethodBean() {
        return new InitMethodBean();
    }


}
