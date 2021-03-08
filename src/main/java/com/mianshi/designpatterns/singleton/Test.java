package com.mianshi.designpatterns.singleton;

/**
 * @author 朱伟伟
 * @date 2021-03-06 12:19:37
 * @description
 */
public class Test {
    public static void main(String[] args) {
        int i = 1;
//        i = i++;
        i = ++i;
        System.out.println("i = " + i);//2
        int j = i++;
        System.out.println("i = " + i);//3
        System.out.println("j = " + j);//2
        int k = i + ++i * i++;// 3+4*4
        System.out.println("i = " + i);//5
        System.out.println("j = " + j);//2
        System.out.println("k = " + k);//19
    }
}
