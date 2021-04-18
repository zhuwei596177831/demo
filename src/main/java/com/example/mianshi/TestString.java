package com.example.mianshi;

/**
 * @author 朱伟伟
 * @date 2021-04-11 21:36:44
 * @description
 */
public class TestString {
    public static void main(String[] args) {
//        String a = "aa";//一个
//        String b = new String("aa");//两个
//        String c = new String("aa") + new String("bb");//5个
//        String d = new String("aa") + new String("bb") + new String("cc");//内存层面new了8个对象

        String s = new String("1") + new String("1");
        String s1 = "11";
//        s = s.intern();
        s.intern();
        System.out.println(s == s1);
    }
}
