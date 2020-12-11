package com.test.getbean.getEarlyBeanReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 朱伟伟
 * @date 2020-12-10 10:47:27
 * @description
 */
@Component
public class EarlyBeanReferenceBeanC {

    public EarlyBeanReferenceBeanC() {
    }

    @Autowired
    EarlyBeanReferenceBeanA earlyBeanReferenceBeanA;
    @Autowired
    EarlyBeanReferenceBeanB earlyBeanReferenceBeanB;
}
