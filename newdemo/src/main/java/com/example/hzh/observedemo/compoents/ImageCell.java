package com.example.hzh.observedemo.compoents;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.hzh.observedemo.utils.LayoutHelper;

/**
 * Created by HZH on 2016/4/18.
 */
public class ImageCell extends FrameLayout {
    private Paint mPaint;
    private boolean isNeed;
    private ImageView imageView;

    public ImageCell(Context context) {
        super(context);
        if (mPaint != null) {
            mPaint.setColor(Color.BLACK);
            mPaint.setStrokeWidth(1);
        }
        setWillNotDraw(false);
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(imageView, LayoutHelper.createFrame(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        //addView(imageView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void setValue(int resId) {
        imageView.setImageResource(resId);
    }

    public void needDivider(boolean isNeed) {
        this.isNeed = isNeed;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isNeed) {
            canvas.drawLine(0, getHeight(), getWidth(), getHeight(), mPaint);
        }
    }
}
