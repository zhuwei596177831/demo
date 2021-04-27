package com.example.mianshi.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2021-04-27 09:57:48
 * @description 应用场景：方法间传参
 * {@link ThreadLocal}
 */
public class MethodParameterTransfer {
//    private static final ThreadLocal<Person> PERSON_THREAD_LOCAL = new ThreadLocal<>();

    //子线程中传递
    private static final ThreadLocal<Person> PERSON_THREAD_LOCAL = new InheritableThreadLocal<>();

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws InterruptedException {
        setData(new Person("朱伟伟"));

//        getAndPrintData();

        //异步任务中获取 1.
//        new Thread(MethodParameterTransfer::getAndPrintData).start();
//        new Thread(MethodParameterTransfer::getAndPrintData).start();
//        setData(new Person("哈哈哈"));
//        new Thread(MethodParameterTransfer::getAndPrintData).start();

        //2.Thread在init(execute)初始化的时候，才会去同步一份最新数据过来。
        executorService = Executors.newFixedThreadPool(2);
//        setData(new Person("哈哈哈"));
        executorService.execute(MethodParameterTransfer::getAndPrintData);
        setData(new Person("哈哈哈"));
        executorService.execute(MethodParameterTransfer::getAndPrintData);
        //复用上面的两个runnable
        TimeUnit.SECONDS.sleep(1);
        executorService.execute(MethodParameterTransfer::getAndPrintData);
        TimeUnit.SECONDS.sleep(1);
        executorService.execute(MethodParameterTransfer::getAndPrintData);
        TimeUnit.SECONDS.sleep(1);
        executorService.execute(MethodParameterTransfer::getAndPrintData);

//        getAndPrintData();
//        Thread thread = new Thread(() -> {
//            Person person = PERSON_THREAD_LOCAL.get();
//            if (person != null) {
//                person.setName("哈哈哈");
//            }
//        });
//        thread.start();
//        thread.join();
//        Thread thread1 = new Thread(MethodParameterTransfer::getAndPrintData);
//        thread1.start();
//        thread1.join();
//        getAndPrintData();

        executorService.shutdown();

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
