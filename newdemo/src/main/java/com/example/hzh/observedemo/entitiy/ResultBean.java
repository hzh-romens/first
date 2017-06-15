package com.example.hzh.observedemo.entitiy;

/**
 * Created by Lanxumit on 2016/11/1.
 */
public class ResultBean {
    private int code;
    private Object result;

    public ResultBean() {

    }

    public ResultBean(int code, Object result) {
        this.code = code;
        this.result = result;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}

