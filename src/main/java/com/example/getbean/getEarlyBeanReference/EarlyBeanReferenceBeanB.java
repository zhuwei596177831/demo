package com.example.getbean.getEarlyBeanReference;

import com.example.getbean.getEarlyBeanReference.aop.TestJdkProxy;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author 朱伟伟
 * @date 2020-11-26 17:16:34
 * @description
 */
@Component
public class EarlyBeanReferenceBeanB {
    /**
     * autowired jdk proxy bean
     * 循环依赖时 EarlyBeanReferenceBeanA async任务必须懒加载注入
     */
//    @Autowired
//    @Lazy
//    TestJdkProxy testJdkProxy;
    @Autowired
    ObjectProvider<EarlyBeanReferenceBeanA> referenceBeanAObjectProvider;


//    @Autowired
//    EarlyBeanReferenceBeanA earlyBeanReferenceBeanA;

    public void testAsyncMethod() {
//        testJdkProxy.earlyReferenceAsyncMethod();
        referenceBeanAObjectProvider.getObject().earlyReferenceAsyncMethod();
    }

}
