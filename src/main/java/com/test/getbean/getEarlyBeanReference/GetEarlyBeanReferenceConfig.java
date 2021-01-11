package com.test.getbean.getEarlyBeanReference;

import com.test.getbean.getEarlyBeanReference.transaction.propagation.OneService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;

/**
 * @author 朱伟伟
 * @date 2020-11-26 17:16:02
 * @description {@link EnableAutoConfiguration} 默认：proxyTargetClass = true 开启cglib代理
 * @see org.springframework.boot.autoconfigure.aop.AopAutoConfiguration
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackageClasses = {GetEarlyBeanReferenceConfig.class})
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
//@EnableTransactionManagement
//@EnableAutoConfiguration
@PropertySource(value = {"classpath:resources.properties"})
public class GetEarlyBeanReferenceConfig {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(GetEarlyBeanReferenceConfig.class);
//        TestJdkProxy testJdkProxy = applicationContext.getBean(TestJdkProxy.class);
//        System.out.println(testJdkProxy instanceof Advised);
//        EarlyBeanReferenceBeanA earlyBeanReferenceBeanA = applicationContext.getBean(EarlyBeanReferenceBeanA.class);
//        System.out.println(earlyBeanReferenceBeanA instanceof Advised);
//        TransactionTestServiceImpl transactionTestService = applicationContext.getBean(TransactionTestServiceImpl.class);
//        System.out.println(transactionTestService instanceof Advised);

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
//        applicationContext.getBean(OneService.class).manualRollBackInsert("one");
//        applicationContext.getBean(AsyncService.class).asyncMethod();
//        Future<String> future = applicationContext.getBean(AsyncService.class).futureMethod();
//        while (!future.isDone()) {
//            System.out.println(future.get());
//        }
//        applicationContext.getBean(EarlyBeanReferenceBeanA.class).earlyReferenceAsyncMethod();
//        applicationContext.getBean(EarlyBeanReferenceBeanB.class).testAsyncMethod();
//        applicationContext.close();
    }

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public InitMethodBean initMethodBean() {
        return new InitMethodBean();
    }


}
