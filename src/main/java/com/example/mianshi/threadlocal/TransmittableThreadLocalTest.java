package com.example.mianshi.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2021-04-27 09:57:48
 * @description {@link com.alibaba.ttl.TransmittableThreadLocal}
 */
public class TransmittableThreadLocalTest {

    private static final ThreadLocal<Person> PERSON_THREAD_LOCAL = new TransmittableThreadLocal<>();
//    private static ExecutorService executorService = TtlExecutors.getTtlExecutorService(Executors.newSingleThreadExecutor());
    private static ExecutorService executorService = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(2));

    public static void main(String[] args) throws InterruptedException {
        setData(new Person("朱伟伟"));
        executorService.execute(TransmittableThreadLocalTest::getAndPrintData);
        TimeUnit.SECONDS.sleep(1);
        setData(new Person("哈哈哈"));
        executorService.execute(TransmittableThreadLocalTest::getAndPrintData);
        TimeUnit.SECONDS.sleep(1);
        setData(new Person("嘿嘿嘿"));
        //复用上面的两个runnable
        executorService.execute(TransmittableThreadLocalTest::getAndPrintData);
        TimeUnit.SECONDS.sleep(1);
        executorService.execute(TransmittableThreadLocalTest::getAndPrintData);
        TimeUnit.SECONDS.sleep(1);
        executorService.execute(TransmittableThreadLocalTest::getAndPrintData);

    }

    private static void setData(Person person) {
        System.out.println("thread name：" + Thread.currentThread().getName());
        PERSON_THREAD_LOCAL.set(person);
    }

    private static void getAndPrintData() {
        System.out.println("thread name：" + Thread.currentThread().getName());
        System.out.println(PERSON_THREAD_LOCAL.get());
    }

    private static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}
