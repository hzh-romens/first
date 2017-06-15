package com.example.hzh.observedemo.presenter;

import com.example.hzh.observedemo.entitiy.ResultBean;
import com.example.hzh.observedemo.model.ISplashModel;
import com.example.hzh.observedemo.model.SplashModel;
import com.example.hzh.observedemo.view.ISpalshView;

/**
 * Created by Lanxumit on 2016/11/1.
 */
public class SplashPresenter {
    private ISpalshView spalshView;
    private SplashModel splashModel;

    public SplashPresenter(ISpalshView spalshView) {
        this.spalshView = spalshView;
        splashModel = new SplashModel();
    }

    public void Splash() {
        splashModel.getData(new ISplashModel.CallBack() {
            @Override
            public void onResult(ResultBean result) {
                //按情况用spalshView
                if (result.getCode() == -1) {
                    spalshView.ShowToast(result.getResult().toString());
                } else if (result.getCode() == 1) {
                    spalshView.ToLaunch();
                } else if (result.getCode() == 2) {
                    spalshView.ToMain();
                } else if (result.getCode() == 3) {
                    spalshView.ToLogin();
                }
            }
        });
    }

}
