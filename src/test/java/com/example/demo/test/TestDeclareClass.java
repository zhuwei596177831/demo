package com.example.demo.test;

/**
 * @author 朱伟伟
 * @date 2020-11-20 14:26:20
 * @description
 */
public class TestDeclareClass {

    public static void main(String[] args) {
        System.out.println(TestDeclareClass.class.getDeclaringClass());
        for (Class<?> declaredClass : TestDeclareClass.class.getDeclaredClasses()) {
            System.out.println(declaredClass.getName());
        }
    }

    private static class InnerClass {
        public static void main(String[] args) {
            System.out.println(InnerClass.class.getDeclaringClass().getName());
        }
    }

    interface DeclareClassInterface {

    }
}