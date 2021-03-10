package com.example.mianshi.designpatterns.factory.generalfactory;

import com.example.mianshi.designpatterns.factory.DellMouse;
import com.example.mianshi.designpatterns.factory.LianXiangMouse;

/**
 * @author 朱伟伟
 * @date 2021-03-08 15:37:24
 * @description
 */
public class GeneralFactoryTest {
    public static void main(String[] args) {
        DellMouseFactory dellMouseFactory = new DellMouseFactory();
        DellMouse dellMouse = (DellMouse) dellMouseFactory.createMouse();

        LianXiangFactory lianXiangFactory = new LianXiangFactory();
        LianXiangMouse lianXiangMouse = (LianXiangMouse) lianXiangFactory.createMouse();
    }
}
