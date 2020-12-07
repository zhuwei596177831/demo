package com.example.getbean.getEarlyBeanReference;

import com.example.getbean.getEarlyBeanReference.aop.TestJdkProxy;
import com.example.getbean.getEarlyBeanReference.multibean.PersonService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.*;

/**
 * @author 朱伟伟
 * @date 2020-11-26 17:16:26
 * @description
 * @see org.springframework.beans.factory.support.RootBeanDefinition#getDestroyMethodName()
 * @see org.springframework.beans.factory.config.ConfigurableBeanFactory#destroySingletons()
 * @see org.springframework.context.ConfigurableApplicationContext#close()
 */
@Component
public class EarlyBeanReferenceBeanA implements InitializingBean, DisposableBean, TestJdkProxy {
    @Autowired
    Optional<EarlyBeanReferenceBeanB> referenceBeanBOptional;
    @Autowired
    ObjectFactory<EarlyBeanReferenceBeanB> referenceBeanBObjectFactory;
    @Autowired
    ObjectProvider<EarlyBeanReferenceBeanB> referenceBeanBObjectProvider;
    @Autowired
    EarlyBeanReferenceBeanB earlyBeanReferenceBeanB;
    @Autowired
    ObjectFactory<Collection<EarlyBeanReferenceBeanB>> collectionObjectFactory;
    @Autowired
    ObjectProvider<List<EarlyBeanReferenceBeanB>> collectionObjectProvider;
    @Value("${test.name}")
    private String name;
    @Value("#{1+1}")
    private String num;
    @Autowired
    EarlyBeanReferenceBeanB[] earlyBeanReferenceBeanBArray;
    @Autowired
    Collection<EarlyBeanReferenceBeanB> beanReferenceBeanBCollection;
    @Autowired
    List<EarlyBeanReferenceBeanB> beanReferenceBeanBList;
    @Autowired
    Set<EarlyBeanReferenceBeanB> beanReferenceBeanBSet;
    @Autowired
    Map<String, EarlyBeanReferenceBeanB> beanReferenceBeanBMap;

    //    @Autowired
    @Resource
    PersonService personService;

    @Override
    public String test(String name) {
//        System.out.println(1 / 0);
        Object currentProxy = AopContext.currentProxy();
        System.out.println("currentProxy......" + currentProxy.getClass());
        System.out.println("this......" + this.getClass());
//        EarlyBeanReferenceBeanA earlyBeanReferenceBeanA = (EarlyBeanReferenceBeanA) currentProxy;
//        earlyBeanReferenceBeanA.currentProxyMethod("");

        /**
         *  jdk proxy bean
         */
        TestJdkProxy testJdkProxy = (TestJdkProxy) currentProxy;
        testJdkProxy.currentProxyMethod("");
        return name;
    }

    @Override
    public void currentProxyMethod(String param) {
        System.out.println("currentProxyMethod......:");
    }

    @PostConstruct
    public void initMethod() {
        System.out.println("EarlyBeanReferenceBeanA initMethod......" + earlyBeanReferenceBeanB);
        System.out.println("EarlyBeanReferenceBeanA initMethod......" + referenceBeanBOptional.get());
        System.out.println("EarlyBeanReferenceBeanA initMethod......" + referenceBeanBObjectFactory.getObject());
        System.out.println("EarlyBeanReferenceBeanA initMethod......" + referenceBeanBObjectProvider.getObject());
        System.out.println("========================");
        System.out.println(collectionObjectFactory.getObject().getClass());
        Collection<EarlyBeanReferenceBeanB> referenceBeanBCollection = collectionObjectFactory.getObject();
        referenceBeanBCollection.forEach(System.out::println);
        System.out.println("========================");
        System.out.println(collectionObjectProvider.getObject().getClass());
        for (EarlyBeanReferenceBeanB beanReferenceBeanB : collectionObjectProvider.getObject()) {
            System.out.println(beanReferenceBeanB);
        }
        System.out.println("========================");
        for (EarlyBeanReferenceBeanB beanReferenceBeanB : earlyBeanReferenceBeanBArray) {
            System.out.println(beanReferenceBeanB);
        }
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("EarlyBeanReferenceBeanA preDestroy......");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("EarlyBeanReferenceBeanA afterPropertiesSet......");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("EarlyBeanReferenceBeanA destroy......");
    }

    /**
     * @author: 朱伟伟
     * @date: 2020-12-07 11:09
     * @description: 循环依赖的bean async测试
     * 报错原因:
     * 该Bean已经EarlyBeanReferenceBeanB提前注入(populateBean()->AbstractAutoProxyCreator->getEarlyBeanReference()),
     *
     * 之后执行当前bean的initializeBean()时,因为该方法用了@Async注解实现多线程，
     * 导致Bean又被代理了一次
     * AsyncAnnotationBeanPostProcessor
     * ->AbstractBeanFactoryAwareAdvisingPostProcessor
     * ->AbstractAdvisingBeanPostProcessor(postProcessAfterInitialization())
     *
     * EarlyBeanReferenceBeanB注入的是AbstractAutoProxyCreator代理的，版本不一致，于是报错。
     * @see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#doCreateBean
     **/
    @Override
    @Async
    public void earlyReferenceAsyncMethod() {
        System.out.println("earlyReferenceAsyncMethod......" + Thread.currentThread().getName());
    }
}
