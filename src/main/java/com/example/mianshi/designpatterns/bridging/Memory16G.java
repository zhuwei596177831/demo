package com.example.mianshi.designpatterns.bridging;

/**
 * @author 朱伟伟
 * @date 2021-03-09 13:45:26
 * @description
 */
public class Memory16G implements Memory {
    @Override
    public void addMemory() {
        System.out.println("安装了16g内存");
    }
}
