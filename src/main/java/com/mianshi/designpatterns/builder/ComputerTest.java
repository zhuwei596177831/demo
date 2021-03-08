package com.mianshi.designpatterns.builder;

/**
 * @author 朱伟伟
 * @date 2021-03-08 16:03:21
 * @description
 */
public class ComputerTest {
    public static void main(String[] args) {
//        Computer.Builder builder = new Computer.Builder("111", "朱伟伟");
//        Computer computer = builder.setEmail("596177831@qq.com").setAddress("砀山").build();
        Computer.Builder builder = new Computer.Builder();
        Computer computer = builder.setId("111").setName("朱伟伟").setEmail("596177831@qq.com").setAddress("砀山").build();
    }
}
