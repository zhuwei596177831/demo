package com.example.mianshi.designpatterns.factory.generalfactory;

import com.example.mianshi.designpatterns.factory.DellMouse;
import com.example.mianshi.designpatterns.factory.Mouse;

/**
 * @author 朱伟伟
 * @date 2021-03-08 15:36:31
 * @description
 */
public class DellMouseFactory implements MouseFactory {
    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }
}
