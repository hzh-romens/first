package com.example.hzh.hackdemo.presenter;

import com.example.hzh.hackdemo.model.ILoginModle;
import com.example.hzh.hackdemo.model.LoginModle;
import com.example.hzh.hackdemo.view.ILoginView;

/**
 * Created by Lanxumit on 2016/11/5.
 */
public class LoginPrestener implements ILoginPrestener{
    private LoginModle loginModle;
    private ILoginView loginView;

    public LoginPrestener(ILoginView loginView) {
        this.loginView = loginView;
        loginModle = new LoginModle();
    }

    @Override
    public void getData() {
        loginModle.getData(new ILoginModle.CallBackListener() {
            @Override
            public void CallBack(String string) {
                //逻辑处理
                loginView.login();
                loginView.showToast("");
            }
        });
    }

}
