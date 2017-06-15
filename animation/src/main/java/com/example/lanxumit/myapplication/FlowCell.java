package com.example.lanxumit.myapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Lanxumit on 2017/4/5.
 */

public class FlowCell extends TextView {
    private Paint paint;

    public FlowCell(Context context) {
        super(context);
        init(context);
    }

    public FlowCell(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FlowCell(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public FlowCell(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void init(Context context) {
        setPadding(16, 4, 16, 4);
        ShapeDrawable press = new ShapeDrawable();
        press.setShape(new PressShape());
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, press);
        ShapeDrawable defaultDrawable = new ShapeDrawable();
        DefaultShape defaultShape = new DefaultShape();
        defaultDrawable.setShape(defaultShape);
        stateListDrawable.addState(new int[]{}, defaultDrawable);
        setBackground(stateListDrawable);
        int[] colors = new int[]{Color.WHITE, Color.BLUE};
        int[][] states = new int[2][];
        states[0] = new int[]{android.R.attr.state_pressed};
        states[1] = new int[]{};
        ColorStateList colorStateList = new ColorStateList(states, colors);
        setTextColor(colorStateList);
        setSingleLine(true);
        setEllipsize(TextUtils.TruncateAt.END);
        setTextSize(18);
    }

    private class PressShape extends Shape {

        @Override
        public void draw(Canvas canvas, Paint paint) {
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLUE);
            canvas.drawRoundRect(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), canvas.getWidth() / 5, canvas.getHeight() / 5, paint);

        }
    }

    private class DefaultShape extends Shape {

        @Override
        public void draw(Canvas canvas, Paint paint) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setAntiAlias(true);
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(1);
            canvas.drawRoundRect(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), canvas.getWidth() / 5, canvas.getHeight() / 5, paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
