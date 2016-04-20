package com.example.hzh.drawabledemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by HZH on 2016/1/6.
 */
public class EmptyCell extends FrameLayout {
    public EmptyCell(Context context) {
        super(context);
        View view = new View(context);
        view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(view);
    }
}
