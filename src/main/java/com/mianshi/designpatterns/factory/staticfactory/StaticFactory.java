package com.mianshi.designpatterns.factory.staticfactory;

import com.mianshi.designpatterns.factory.DellMouse;
import com.mianshi.designpatterns.factory.LianXiangMouse;
import com.mianshi.designpatterns.factory.Mouse;

/**
 * @author 朱伟伟
 * @date 2021-03-08 15:33:59
 * @description
 */
public class StaticFactory {


    public static Mouse createMouse(Integer type) {
        switch (type) {
            case 1:
                return new DellMouse();
            case 2:
                return new LianXiangMouse();
            default:
                return new Mouse() {
                    @Override
                    public void test() {
                        System.out.println("未知");
                    }
                };
        }
    }

}
