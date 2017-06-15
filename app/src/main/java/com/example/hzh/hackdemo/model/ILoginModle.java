package com.example.hzh.hackdemo.model;

/**
 * Created by Lanxumit on 2016/11/5.
 */
public interface ILoginModle {
    void getData(CallBackListener callBack);

    public interface CallBackListener {
        void CallBack(String string);
    }

    ;
}
