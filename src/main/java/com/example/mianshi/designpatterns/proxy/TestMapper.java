package com.example.mianshi.designpatterns.proxy;


/**
 * @author 朱伟伟
 * @date 2021-03-10 15:31:11
 * @description
 */
public interface TestMapper {
    @MySelect("select * from transaction_test where id = #{id}")
    String getList(String id);
}
