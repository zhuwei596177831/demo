package com.example.okhttp.retrofit.entity;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

/**
 * @author 朱伟伟
 * @date 2020-12-18 00:23:37
 * @description
 */
public class ScenicHttpResult<T> implements Serializable {
    private static final long serialVersionUID = 2526144038096887543L;
    private String status;
    private String message;
    private List<T> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
