package com.example.mianshi.designpatterns.bridging;

/**
 * @author 朱伟伟
 * @date 2021-03-09 13:46:23
 * @description
 */
public abstract class Phone {
    protected final Memory memory;

    protected Phone(Memory memory) {
        this.memory = memory;
    }

    public abstract void buyPhone();
}
