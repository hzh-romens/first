package com.example.hzh.observedemo.model;

import com.example.hzh.observedemo.entitiy.ResultBean;

/**
 * Created by Lanxumit on 2016/11/1.
 */
public interface ISplashModel extends IBaseModel {
    void getData(CallBack callBack);

    public interface CallBack {
        public void onResult(ResultBean result);
    }
}
