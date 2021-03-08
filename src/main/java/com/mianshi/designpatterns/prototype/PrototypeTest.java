package com.mianshi.designpatterns.prototype;

/**
 * @author 朱伟伟
 * @date 2021-03-08 17:16:58
 * @description
 */
public class PrototypeTest {
    public static void main(String[] args) {
        Person person = new Person("朱伟伟", "zww");
        System.out.println(person);
        Person clone = (Person) person.clone();
        System.out.println(clone);
        clone.setName("哈哈哈");
        System.out.println(person);
        System.out.println(clone);
        Person clone1 = (Person) person.clone();
        System.out.println(clone1);
    }
}
