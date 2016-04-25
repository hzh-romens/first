package com.example.hzh.observedemo.utils;


import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by HZH on 2016/4/18.
 */
public class LayoutHelper {
    public static FrameLayout.LayoutParams createFrame(int width, int height, int gravity, int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, height);
        layoutParams.setMargins(paddingLeft, paddingTop, paddingRight, paddingBottom);
        layoutParams.gravity = gravity;
        return layoutParams;
    }

    public static FrameLayout.LayoutParams createFrame(int width, int height, int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, height);
        layoutParams.setMargins(paddingLeft, paddingTop, paddingRight, paddingBottom);
        return layoutParams;
    }

    public static FrameLayout.LayoutParams createFrame(int width, int height) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, height);
        return layoutParams;
    }

    public static FrameLayout.LayoutParams createFrame(int width, int height, int gravity) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, height);
        layoutParams.gravity = gravity;
        return layoutParams;
    }


    public static LinearLayout.LayoutParams createLine(int width, int height, int gravity, int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        layoutParams.setMargins(paddingLeft, paddingTop, paddingRight, paddingBottom);
        layoutParams.gravity = gravity;
        return layoutParams;
    }

    public static RelativeLayout.LayoutParams createRela(int width, int height, int gravity, int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
        layoutParams.setMargins(paddingLeft, paddingTop, paddingRight, paddingBottom);
        return layoutParams;
    }
}

