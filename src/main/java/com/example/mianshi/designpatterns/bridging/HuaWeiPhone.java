package com.example.mianshi.designpatterns.bridging;

/**
 * @author 朱伟伟
 * @date 2021-03-09 13:48:32
 * @description
 */
public class HuaWeiPhone extends Phone {

    public HuaWeiPhone(Memory memory) {
        super(memory);
    }

    @Override
    public void buyPhone() {
        this.memory.addMemory();
        System.out.println("购买了华为手机");
    }
}
