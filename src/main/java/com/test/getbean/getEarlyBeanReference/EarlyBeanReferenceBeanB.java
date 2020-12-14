package com.test.getbean.getEarlyBeanReference;

import com.test.getbean.getEarlyBeanReference.aop.TestJdkProxy;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 朱伟伟
 * @date 2020-11-26 17:16:34
 * @description
 */
@Component
public class EarlyBeanReferenceBeanB {
//    @Autowired
//    @Lazy
//    TestJdkProxy testJdkProxy;

    /**
     * autowired jdk proxy bean
     * 循环依赖的bean互相注入时一方有async任务必须懒加载注入
     * 其它bean注入不用
     */
    @Autowired
    ObjectProvider<EarlyBeanReferenceBeanA> referenceBeanAObjectProvider;


//    @Autowired
//    EarlyBeanReferenceBeanA earlyBeanReferenceBeanA;

    //    @Transactional
    public void testAsyncMethod() {
//        testJdkProxy.earlyReferenceAsyncMethod();
        referenceBeanAObjectProvider.getObject().earlyReferenceAsyncMethod();
    }

}
