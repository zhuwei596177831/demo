package com.example.getbean.getEarlyBeanReference.async;

import java.util.concurrent.Future;

/**
 * @author 朱伟伟
 * @date 2020-12-04 10:53:24
 * @description
 */
public interface AsyncService {
    void asyncMethod();

    /**
     * @author: 朱伟伟
     * @date: 2020-12-04 16:50
     * @description: 异步任务返回结果
     **/
    Future<String> futureMethod();
}
