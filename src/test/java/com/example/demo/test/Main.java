package com.example.demo.test;

/**
 * @author 朱伟伟
 * @date 2021-01-25 10:29:03
 * @description
 */
public class Main {
    public static void main(String[] args) {
//        Main main = (Main) null;
//        System.out.println(main);
//        main.doSomething();
//        doSomething();
        Integer i1 = 100;
        Integer i2 = 100;
        System.out.println(i1 < i2);
        System.out.println(i1 == i2);
        System.out.println(System.identityHashCode(i1));
        System.out.println(System.identityHashCode(i2));
        Integer i3 = 200;
        Integer i4 = 200;
        System.out.println(i3 == i4);
        System.out.println(System.identityHashCode(i3));
        System.out.println(System.identityHashCode(i4));
        System.out.println("********");
        Integer i5 = 100;
        Long i6 = 100L;
        System.out.println(i5.equals(i6)); //false
    }

    private static void doSomething() {
        System.out.println("doSomething");
    }

}
