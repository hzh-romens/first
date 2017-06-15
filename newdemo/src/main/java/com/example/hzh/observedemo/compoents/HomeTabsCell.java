package com.example.hzh.observedemo.compoents;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Lanxumit on 2016/11/2.
 */
public class HomeTabsCell extends LinearLayout {
    private Context context;
    private ViewPager viewPager;

    public HomeTabsCell(Context context) {
        super(context);
        this.context = context;
    }

    public HomeTabsCell(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeTabsCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void addItem(String title, int resId, int index) {
        HomeTabCell cell = new HomeTabCell(getContext(), title, resId);
        cell.setTag(getChildCount());
        cell.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager != null) {
                    int index = Integer.parseInt(v.getTag().toString());
                    viewPager.setCurrentItem(index);
                    setChecked(index);

                }
            }
        });
        addView(cell, index, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
    }

    public void setViewPager(final ViewPager viewPager) {
        this.viewPager = viewPager;
        this.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setChecked(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setChecked(int pos) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ((HomeTabCell) this.getChildAt(i)).setChecked(pos == i);
        }
    }
}
