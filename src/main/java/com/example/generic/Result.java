package com.example.generic;

import com.github.pagehelper.Page;

import java.util.Collection;

/**
 * @author 朱伟伟
 * @date 2020-12-27 11:52:23
 * @description
 */
public class Result<T> {
    private String code;
    private String msg;
    private T data;

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> ok() {
        return new Result<>("0000", "成功", null);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>("0000", "成功", data);
    }

    public static <T> Result<T> fail() {
        return new Result<>("0001", "失败", null);
    }

    public static <E> Result<ArrayData<E>> ok(Collection<E> data) {
        if (data instanceof Page) {
            Page<E> page = (Page<E>) data;
            return new Result<>("0000", "成功", new ArrayData<>(page, page.getTotal()));
        }
        return new Result<>("0000", "成功", new ArrayData<>(data, data.size()));
    }

    public static <E> Result<ArrayData<E>> ok(com.baomidou.mybatisplus.extension.plugins.pagination.Page<E> page) {
        return new Result<>("0000", "成功", new ArrayData<>(page.getRecords(), page.getTotal()));
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
