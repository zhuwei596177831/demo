package com.example.mianshi.designpatterns.bridging;

/**
 * @author 朱伟伟
 * @date 2021-03-09 13:48:32
 * @description
 */
public class XiaoMiPhone extends Phone {

    public XiaoMiPhone(Memory memory) {
        super(memory);
    }

    @Override
    public void buyPhone() {
        this.memory.addMemory();
        System.out.println("购买了小米手机");
    }
}
