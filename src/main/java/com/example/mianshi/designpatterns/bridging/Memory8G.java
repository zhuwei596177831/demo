package com.example.mianshi.designpatterns.bridging;

/**
 * @author 朱伟伟
 * @date 2021-03-09 13:45:26
 * @description
 */
public class Memory8G implements Memory {
    @Override
    public void addMemory() {
        System.out.println("安装了8g内存");
    }
}
