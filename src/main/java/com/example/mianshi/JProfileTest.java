package com.example.mianshi;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 朱伟伟
 * @date 2021-04-17 14:05:15
 * @description JProfile工具测试
 */
public class JProfileTest {

    private static List<String> strings = new LinkedList<>();
    private static Date date = new Date();

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            strings.add(String.valueOf(i));
        }
        System.out.println("数据添加完毕，请操作...");
        new Scanner(System.in).next();
        strings = null;
        date = null;
        System.out.println("strings、date已置空，请操作...");
        new Scanner(System.in).next();
        System.out.println("结束");
    }

}
