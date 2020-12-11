package com.test.getbean.getEarlyBeanReference.transaction.propagation;

/**
 * @author 朱伟伟
 * @date 2020-12-04 09:35:19
 * @description
 */
public interface OneService {
    void insert(String name);
    void manualRollBackInsert(String name);
}
