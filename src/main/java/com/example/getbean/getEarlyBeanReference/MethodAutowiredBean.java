package com.example.getbean.getEarlyBeanReference;

import com.example.getbean.getEarlyBeanReference.aop.TestJdkProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 朱伟伟
 * @date 2020-11-30 10:21:51
 * @description
 */
@Component
public class MethodAutowiredBean {
    //    private EarlyBeanReferenceBeanA earlyBeanReferenceBeanA;
    private TestJdkProxy testJdkProxy;
    private EarlyBeanReferenceBeanB earlyBeanReferenceBeanB;

//    @Autowired
//    public void autowiredBean(EarlyBeanReferenceBeanA earlyBeanReferenceBeanA, EarlyBeanReferenceBeanB earlyBeanReferenceBeanB) {
//        this.earlyBeanReferenceBeanA = earlyBeanReferenceBeanA;
//        this.earlyBeanReferenceBeanB = earlyBeanReferenceBeanB;
//    }

    /**
     * autowired jdk proxy bean
     */
    @Autowired
    public void autowiredBean(TestJdkProxy testJdkProxy, EarlyBeanReferenceBeanB earlyBeanReferenceBeanB) {
        this.testJdkProxy = testJdkProxy;
        this.earlyBeanReferenceBeanB = earlyBeanReferenceBeanB;
    }

}
