package com.example.hzh.observedemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hzh.observedemo.MyApplication;

/**
 * Created by Lanxumit on 2016/11/1.
 */
public class SharedPerfrenceUtils {
    SharedPreferences sharedPreferences = MyApplication.context.getSharedPreferences("demo", Context.MODE_PRIVATE);
    private static SharedPerfrenceUtils instance = null;
    public static Object sync = new Object();

    public static synchronized SharedPerfrenceUtils getInstance() {
        if (instance == null) {
            synchronized (SharedPerfrenceUtils.class) {
                if (instance == null) {
                    instance = new SharedPerfrenceUtils();
                }
            }
        }
        return instance;
    }

    public void saveKey(String key, String value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public String getValue(String key) {
        String string = sharedPreferences.getString(key, "");
        return string;
    }

    public boolean isLaunched(String key) {
        boolean isLaunch = sharedPreferences.getBoolean(key, false);
        return isLaunch;
    }

    public void saveLaunched(String key, boolean value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }
}
