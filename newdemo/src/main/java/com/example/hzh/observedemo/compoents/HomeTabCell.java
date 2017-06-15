package com.example.hzh.observedemo.compoents;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikepenz.materialize.util.UIUtils;

/**
 * Created by HZH on 2016/4/20.
 */
public class HomeTabCell extends FrameLayout {
    private Paint mPaint;
    private LinearLayout linearLayout;
    private TextView titleView;
    private ImageView iconView;
    private Context context;
    private FrameLayout itemView;

    public HomeTabCell(Context context, String title, int resId) {
        super(context);
        this.context = context;
        if (mPaint != null) {
            mPaint = new Paint();
            mPaint.setColor(Color.BLACK);
            mPaint.setStrokeWidth(1);
        }
        setWillNotDraw(false);
        initView(context);
        addTabCell(title, resId);
    }

    public HomeTabCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public HomeTabCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        addView(linearLayout, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) UIUtils.convertDpToPixel(48, context)));
    }


    public void addTabCell(String title, int resId) {
        itemView = new FrameLayout(context);
        titleView = new TextView(context);
        titleView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        titleView.setText(title);
        titleView.setGravity(Gravity.CENTER);
        titleView.setTextColor(Color.BLACK);
        itemView.addView(titleView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM));
        iconView = new ImageView(context);
        iconView.setImageResource(resId);
        itemView.addView(iconView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.TOP));
        linearLayout.addView(itemView, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
    }

    public void setChecked(boolean selected) {
        if (selected) {
            titleView.setTextColor(Color.BLUE);
            iconView.setColorFilter(Color.BLUE);
        } else {
            titleView.setTextColor(Color.BLACK);
            iconView.setColorFilter(Color.BLACK);
        }
    }
}
