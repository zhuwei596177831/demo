package com.example.mianshi.designpatterns.bridging;

/**
 * @author 朱伟伟
 * @date 2021-03-09 13:50:09
 * @description
 */
public class BridgingTest {
    public static void main(String[] args) {
        Phone huawei = new HuaWeiPhone(new Memory8G());
        huawei.buyPhone();

        Phone xiaomi = new XiaoMiPhone(new Memory16G());
        xiaomi.buyPhone();
    }
}
