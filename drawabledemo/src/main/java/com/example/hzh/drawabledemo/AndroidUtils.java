package com.example.hzh.drawabledemo;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by HZH on 2016/1/5.
 */
public class AndroidUtils {
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }
}
