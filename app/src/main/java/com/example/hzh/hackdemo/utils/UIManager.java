package com.example.hzh.hackdemo.utils;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Lanxumit on 2016/11/1.
 */
public class UIManager {
    public static void ToOtherActivity(Activity context, String className) {
        Intent intent = new Intent();
        intent.setClassName(context, className);
        context.startActivity(intent);
        context.finish();
    }
}
