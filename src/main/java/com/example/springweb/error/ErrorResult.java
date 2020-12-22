package com.example.springweb.error;

import java.io.Serializable;

/**
 * @author 朱伟伟
 * @date 2020-12-22 17:36:01
 * @description
 */
public class ErrorResult<T> implements Serializable {
    private static final long serialVersionUID = -4940414349908618525L;
    private Integer code;
    private String message;
    private T data;

    public ErrorResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorResult() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
