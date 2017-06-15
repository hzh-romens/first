package com.example.hzh.observedemo.utils;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by pc on 2016/4/25.
 */
//Intent工具类
public class UIOpenhelper {
    public static void ToOther(Activity context, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        context.startActivity(intent);
        context.finish();
    }
}
