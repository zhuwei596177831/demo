package com.example.mianshi.designpatterns.factory.generalfactory;

import com.example.mianshi.designpatterns.factory.LianXiangMouse;
import com.example.mianshi.designpatterns.factory.Mouse;

/**
 * @author 朱伟伟
 * @date 2021-03-08 15:36:58
 * @description
 */
public class LianXiangFactory implements MouseFactory {
    @Override
    public Mouse createMouse() {
        return new LianXiangMouse();
    }
}
