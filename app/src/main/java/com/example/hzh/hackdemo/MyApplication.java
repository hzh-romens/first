package com.example.hzh.hackdemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by HZH on 2015/11/30.
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getContext() {
        return MyApplication.context;
    }
}
