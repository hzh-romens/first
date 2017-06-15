package com.example.hzh.hackdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.hzh.hackdemo.presenter.LoginPrestener;
import com.example.hzh.hackdemo.view.ILoginView;

/**
 * Created by Lanxumit on 2016/11/5.
 */
public class LoginActivity extends Activity implements ILoginView {
    private LoginPrestener loginPrestener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPrestener = new LoginPrestener(this);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPrestener.getData();
            }
        });
    }

    @Override
    public void login() {

    }

    @Override
    public void showToast(String info) {

    }
}
