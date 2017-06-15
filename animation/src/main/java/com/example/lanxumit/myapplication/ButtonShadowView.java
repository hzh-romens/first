package com.example.lanxumit.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Lanxumit on 2017/3/16.
 */
public class ButtonShadowView extends View {
    private Context mContext;
    private Paint paint;
    private int mLeft = 0;
    private int mRight = 0;
    private int mTop = 0;
    private int mBottom = 0;

    public ButtonShadowView(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(2);

        //设置阴影效果
        //  paint.setShadowLayer(10, 3, 3, 0xff00ccff);
    }

    public ButtonShadowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ButtonShadowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public ButtonShadowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    public void setParams(int left, int top, int right, int bottom) {
        this.mLeft = left;
        this.mTop = top;
        this.mRight = right;
        this.mBottom = bottom;
    }

    private String name;

    public void setValue(String name) {
        this.name = name;
    }

    private int mWidth, mHeight;

    public void setArea(int width, int height) {

        this.mWidth = width;
        this.mHeight = height;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画矩形
        paint.setColor(0xff0066ff);
        RectF rect = new RectF(mLeft - 10, mTop, mRight + 10, mBottom);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(rect, 8, 8, paint);


        //画文字
        paint.setColor(Color.WHITE);
        paint.setTextSize(36);
        float length = paint.measureText(name, 0, name.length());
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int centerX = (int) ((mRight - mLeft + 20) / 2);
        int centerY = (mBottom - mTop) / 2;
        canvas.drawText(name, centerX + mLeft - length / 2, centerY + mTop - (fontMetricsInt.ascent + fontMetricsInt.descent) / 2
                , paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
