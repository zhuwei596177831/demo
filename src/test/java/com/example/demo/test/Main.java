package com.example.demo.test;

import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;
import org.springframework.objenesis.instantiator.ObjectInstantiator;

import java.io.IOException;
import java.lang.reflect.Constructor;

/**
 * @author 朱伟伟
 * @date 2021-01-25 10:29:03
 * @description
 */
public class Main {
    public static void main(String[] args) throws Exception {
//        Main main = (Main) null;
//        System.out.println(main);
//        main.doSomething();
//        doSomething();
//        Integer i1 = 100;
//        Integer i2 = 100;
//        System.out.println(i1 < i2);
//        System.out.println(i1 == i2);
//        System.out.println(System.identityHashCode(i1));
//        System.out.println(System.identityHashCode(i2));
//        Integer i3 = 200;
//        Integer i4 = 200;
//        System.out.println(i3 == i4);
//        System.out.println(System.identityHashCode(i3));
//        System.out.println(System.identityHashCode(i4));
//        System.out.println("********");
//        Integer i5 = 100;
//        Long i6 = 100L;
//        System.out.println(i5.equals(i6)); //false
//        Demo.class.newInstance();

//        Constructor<Demo> declaredConstructor = Demo.class.getDeclaredConstructor(String.class);
//        declaredConstructor.setAccessible(true);
//        declaredConstructor.newInstance("ddd");

        Objenesis objenesis = new ObjenesisStd();
        Demo demo = objenesis.newInstance(Demo.class);
        Demo demo1 = objenesis.newInstance(Demo.class);
        System.out.println(demo);

        ObjectInstantiator<Demo> demoObjectInstantiator = objenesis.getInstantiatorOf(Demo.class);
        demo = demoObjectInstantiator.newInstance();

    }

    private static void doSomething() {
        System.out.println("doSomething");
    }

}

class Demo {
    private String name;

    private Demo() {
    }

    private Demo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "name='" + name + '\'' +
                '}';
    }
}
