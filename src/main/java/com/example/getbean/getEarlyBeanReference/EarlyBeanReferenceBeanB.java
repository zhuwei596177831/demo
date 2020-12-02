package com.example.getbean.getEarlyBeanReference;

import com.example.getbean.getEarlyBeanReference.aop.TestJdkProxy;
import org.springframework.beans.factory.annotation.Autowired;
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
     */
    @Autowired
    TestJdkProxy testJdkProxy;
//    @Autowired
//    EarlyBeanReferenceBeanA earlyBeanReferenceBeanA;
}
