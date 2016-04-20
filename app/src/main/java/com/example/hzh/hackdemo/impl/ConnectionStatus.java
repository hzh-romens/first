package com.example.hzh.hackdemo.impl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.hzh.hackdemo.MyApplication;
import com.example.hzh.hackdemo.model.IConnectionStatus;

/**
 * Created by HZH on 2015/11/30.
 */
public class ConnectionStatus implements IConnectionStatus{
    @Override
    public boolean isOnline() {
        ConnectivityManager connectivityManager= (ConnectivityManager) MyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo!=null){
           return activeNetworkInfo.isAvailable();
        }
        return false;
    }
}
