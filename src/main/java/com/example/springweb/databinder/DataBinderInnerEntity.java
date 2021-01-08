package com.example.springweb.databinder;

/**
 * @author 朱伟伟
 * @date 2021-01-08 14:57:04
 * @description
 */
public class DataBinderInnerEntity {

    public DataBinderInnerEntity(String email) {
        this.email = email;
    }

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
