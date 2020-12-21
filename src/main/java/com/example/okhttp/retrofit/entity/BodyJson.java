package com.example.okhttp.retrofit.entity;

/**
 * @author 朱伟伟
 * @date 2020-12-21 16:33:02
 * @description
 */
public class BodyJson {
    private String name;
    private String email;

    public BodyJson(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
