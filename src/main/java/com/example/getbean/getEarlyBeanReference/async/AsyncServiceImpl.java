package com.example.getbean.getEarlyBeanReference.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author 朱伟伟
 * @date 2020-12-04 10:53:34
 * @description
 */
@Service
public class AsyncServiceImpl implements AsyncService {
    @Override
    @Async
    public void asyncMethod() {
        System.out.println("asyncMethod......" + Thread.currentThread().getName());
    }

    @Override
    @Async
    public Future<String> futureMethod() {
        System.out.println("futureMethod......" + Thread.currentThread().getName());
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return new AsyncResult<>("哈哈哈");
    }
}
