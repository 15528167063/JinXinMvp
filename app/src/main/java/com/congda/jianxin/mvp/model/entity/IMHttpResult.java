package com.congda.jianxin.mvp.model.entity;

public class IMHttpResult<T> {
    public String code;
    public String status;
    public String message;
    public T data;
}