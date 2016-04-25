package com.example.hzh.observedemo.compoents;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

/**
 * Created by HZH on 2016/4/20.
 */
public class HomeTabCell extends FrameLayout {
    private Paint mPaint;
    private ViewPager viewPager;

    public HomeTabCell(Context context) {
        super(context);
        if (mPaint != null) {
            mPaint=new Paint();
            mPaint.setColor(Color.BLACK);
            mPaint.setStrokeWidth(1);
        }
        setWillNotDraw(false);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public void addTabCell(String title, int resId) {

    }
}
