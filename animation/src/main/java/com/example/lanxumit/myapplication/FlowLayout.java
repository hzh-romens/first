package com.example.lanxumit.myapplication;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by Lanxumit on 2017/4/5.
 */

public class FlowLayout extends ViewGroup {
    private Paint paint;

    public FlowLayout(Context context) {
        super(context);
        initview(context);
    }


    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initview(context);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initview(context);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initview(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    private void initview(Context context) {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        addChild(context);
    }

    private void addChild(Context context) {
        Canvas canvas = new Canvas();
        StateListDrawable stateList = new StateListDrawable();
        stateList.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, context.getResources().getDrawable(R.drawable.button_pressed));
        stateList.addState(new int[]{android.R.attr.state_enabled}, context.getResources().getDrawable(R.drawable.button_pressed));
    }

}
