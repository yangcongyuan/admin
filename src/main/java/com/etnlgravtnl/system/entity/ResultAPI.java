package com.etnlgravtnl.system.entity;

/**
 * Created by admin on 2016/6/20.
 */
public class ResultAPI<T> {

    private String  access_result;   //返回值  0|1
    private  T  msg;

    public String getAccess_result() {
        return access_result;
    }

    public void setAccess_result(String access_result) {
        this.access_result = access_result;
    }

    public T getMsg() {
        return msg;
    }

    public void setMsg(T msg) {
        this.msg = msg;
    }
}
