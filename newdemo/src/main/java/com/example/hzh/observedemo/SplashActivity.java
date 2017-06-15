package com.example.hzh.observedemo;

import android.os.Bundle;
import android.widget.Toast;

import com.example.hzh.observedemo.activity.BaseActivity;
import com.example.hzh.observedemo.activity.LaunchActivity;
import com.example.hzh.observedemo.activity.LoginActivity;
import com.example.hzh.observedemo.activity.MainActivity;
import com.example.hzh.observedemo.presenter.SplashPresenter;
import com.example.hzh.observedemo.utils.UIOpenhelper;
import com.example.hzh.observedemo.view.ISpalshView;

/**
 * Created by Lanxumit on 2016/11/1.
 */
public class SplashActivity extends BaseActivity implements ISpalshView {
    private SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        presenter = new SplashPresenter(this);
        presenter.Splash();
    }

    @Override
    public void ToLaunch() {
        UIOpenhelper.ToOther(this, LaunchActivity.class);
    }

    @Override
    public void ToLogin() {
        UIOpenhelper.ToOther(this, LoginActivity.class);
    }

    @Override
    public void ToMain() {
        UIOpenhelper.ToOther(this, MainActivity.class);
    }

    @Override
    public void ShowToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
