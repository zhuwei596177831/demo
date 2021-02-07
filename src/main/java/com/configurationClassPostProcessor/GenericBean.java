package com.configurationClassPostProcessor;

/**
 * @author 朱伟伟
 * @date 2021-02-07 09:08:28
 * @description
 */
public class GenericBean<T, W> {
    private T t;
    private W w;

    public GenericBean(T t, W w) {
        this.t = t;
        this.w = w;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public W getW() {
        return w;
    }

    public void setW(W w) {
        this.w = w;
    }

    @Override
    public String toString() {
        return "GenericBean{" +
                "t=" + t +
                ", w=" + w +
                '}';
    }
}
