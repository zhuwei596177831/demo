package com.jvm;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2021-03-12 20:45:55
 * @description
 */
public class TestLocalVaribleTable {
    public static void main(String[] args) {
        String s = "111";
        test("sss", 26);
//        try {
//            TimeUnit.SECONDS.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public static String test(String name, Integer age) {
        TestLocalVaribleTable testLocalVaribleTable = new TestLocalVaribleTable();
        Integer num = 10;
        LocalDateTime now = LocalDateTime.now();
        System.out.println(num);
        return "111";
    }

}
