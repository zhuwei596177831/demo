package com.test.getbean.getEarlyBeanReference;

import com.test.getbean.getEarlyBeanReference.transaction.TransactionTestService;
import com.test.getbean.getEarlyBeanReference.transaction.propagation.OneService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;

/**
 * @author 朱伟伟
 * @date 2020-11-26 17:16:02
 * @description {@link EnableAutoConfiguration} 默认：proxyTargetClass = true 开启cglib代理
 * @see org.springframework.boot.autoconfigure.aop.AopAutoConfiguration
 * 关于final方法
 * - JDK代理：因为接口的方法不能使用final关键字，所以编译器就过不去
 * - CGLIB代理：final修饰某个方法后，不报错。但也不会拦截了
 * 关于static方法
 * - JDK代理：static修饰接口上的方法，要求有body体(JDK8后支持)。但是因为子类不能@Override了，所以编译就报错了
 * - CGLIB代理：父类方法用static修饰后，子类也是无法进行重写的。因此不抱错，但也不会拦截了
 * 使用代理的时候，尽量不要使用final和static关键字
 *
 * 关于非public方法
 * - JDK代理：接口中的方法都是public的，所以对于它不存在这种现象
 * - CGLIB代理：记住结论 只有private的方法不能被代理（因为子类无法访问），其余的访问权限级别的，都能够被正常代理。
 * 简单的说就是只要子类能够访问的权限，都能够被正常代理
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackageClasses = {GetEarlyBeanReferenceConfig.class})
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
//@EnableAspectJAutoProxy
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
//        applicationContext.getBean(TransactionTestService.class).toString();
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
