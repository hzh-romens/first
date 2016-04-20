package com.example.hzh.drawabledemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;

/**
 * Created by HZH on 2016/1/5.
 */
public class BitmapUtils {
    public static Bitmap getBitmap(Context context, int resId, int count) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        paint.setDither(true);

        canvas.drawBitmap(bitmap, new Rect(0, 0, width, height), new Rect(0, 0, width, height), paint);

        Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setColor(Color.RED);
        RectF rectF = new RectF(2, height / 2, width - 2, height - 2);
        canvas.drawRoundRect(rectF, 4, 4, paint1);

        Paint paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setTextSize(height/2);
        paint2.setTypeface(Typeface.DEFAULT_BOLD);

        Paint.FontMetricsInt fontMetricsInt = paint2.getFontMetricsInt();

        float textY = ((rectF.bottom - fontMetricsInt.bottom) - (fontMetricsInt.top - rectF.top)) / 2;
        paint2.setTextAlign(Paint.Align.CENTER);
        String countStr;
        if (count > 99) {
            countStr = "99+";
        } else {
            countStr = String.valueOf(count);
        }
        canvas.drawText(countStr, rectF.centerX() - countStr.length() / 2, textY, paint2);
        return newBitmap;
    }
}
