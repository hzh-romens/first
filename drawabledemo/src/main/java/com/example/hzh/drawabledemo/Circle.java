package com.example.hzh.drawabledemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by HZH on 2016/1/5.
 */
public class Circle extends View {
    private Paint mPaint;
    private Context mContext;

    public Circle(Context context) {
        super(context);
        initPaint();
        this.mContext = context;
    }

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        this.mContext = context;
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        /*
09.     * 设置画笔样式为描边，圆环嘛……当然不能填充不然就么意思了
10.     *
11.     * 画笔样式分三种：
12.     * 1.Paint.Style.STROKE：描边
13.     * 2.Paint.Style.FILL_AND_STROKE：描边并填充
14.     * 3.Paint.Style.FILL：填充
15.     */
        mPaint.setStyle(Paint.Style.STROKE);
        //设置画笔的颜色
        mPaint.setColor(Color.LTGRAY);
        //设置描边的粗细,单位px
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(AndroidUtils.getScreenWidth(mContext) / 2, AndroidUtils.getScreenHeight(mContext) / 2, 50, mPaint);
    }
}
