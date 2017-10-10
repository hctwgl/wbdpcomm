package com.wbdp.wx.model;

/**
 * http请求返回的最外层
 * Created by wisedata005 on 2017/7/4.
 */
public class Result<T> {
    private Integer code;

    private String msg;

    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
    @Override
    public String toString() {
        return "{\"code\": "+code+",\"msg\": \""+msg+"\",\"data\": "+data+"}";
    }
}
