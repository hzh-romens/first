package com.example.hzh.observedemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.hzh.observedemo.MyApplication;

/**
 * Created by Lanxumit on 2016/11/1.
 */
public class NetUtils {
    public static boolean getNetStatus() {

        ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null)
        {
            return activeNetworkInfo.isAvailable();
        }
        return false;
    }
}
