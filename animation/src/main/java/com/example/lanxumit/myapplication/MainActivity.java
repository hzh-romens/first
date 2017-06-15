package com.example.lanxumit.myapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button one, two;
    private float oneX = 0;
    private float oneY = 0;
    int left;
    int right;
    int top;
    int bottom;

    private FrameLayout container;
    private FlowCell flowCell;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (FrameLayout) findViewById(R.id.container);
        one = (Button) findViewById(R.id.buttonOne);
        two = (Button) findViewById(R.id.buttonTwo);
        flowCell = (FlowCell) findViewById(R.id.flowcell);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        one.setOnTouchListener(new View.OnTouchListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Display defaultDisplay = getWindow().getWindowManager().getDefaultDisplay();
                int width = defaultDisplay.getWidth();
                int height = defaultDisplay.getHeight();
                int viewWidth = v.getWidth();
                int viewHeght = v.getHeight();
                int virtualBarHeigh = getVirtualBarHeigh(MainActivity.this);

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        oneX = event.getRawX();
                        oneY = event.getRawY();
                        left = one.getLeft();
                        right = one.getRight();
                        top = one.getTop();
                        bottom = one.getBottom();
                        if (buttonShadowView != null) {
                            buttonShadowView.setVisibility(View.VISIBLE);
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        one.setVisibility(View.GONE);
                        float x = event.getRawX();
                        float y = event.getRawY();
                        float distanceX = x - oneX;
                        float distanceY = y - oneY;
                        int moveLeft = 0, moveRight = 0, moveTop = 0, moveBottom = 0;
                        if (left + distanceX > 0 && left + distanceX < width - viewWidth) {
                            moveLeft = (int) (left + distanceX);
                        } else if (left + distanceX < 0) {
                            moveLeft = 0;
                        } else if (left + distanceX > width - viewWidth) {
                            moveLeft = width - viewWidth;
                        }
                        if (right + distanceX > viewWidth && right + distanceX < width) {
                            moveRight = (int) (right + distanceX);
                        } else if (right + distanceX < viewWidth) {
                            moveRight = viewWidth;
                        } else if (right + distanceX > width) {
                            moveRight = width;
                        }

                        if (top + distanceY > toolbar.getHeight() && top + distanceY < height - viewHeght - virtualBarHeigh) {
                            moveTop = (int) (top + distanceY);
                        } else if (top + distanceY < toolbar.getHeight()) {
                            moveTop = toolbar.getHeight();
                        } else if (top + distanceY > height - viewHeght - virtualBarHeigh) {
                            moveTop = height - viewHeght - virtualBarHeigh;
                        }

                        if (bottom + distanceY < height - virtualBarHeigh && bottom + distanceY > viewHeght + toolbar.getHeight()) {
                            moveBottom = (int) (bottom + distanceY);
                        } else if (bottom + distanceY > height - virtualBarHeigh) {
                            moveBottom = height - virtualBarHeigh;
                        } else if (bottom + distanceY < viewHeght + toolbar.getHeight()) {
                            moveBottom = viewHeght + toolbar.getHeight();
                        }
                        drawShadow(moveLeft + one.getPaddingLeft() / 2, moveTop + one.getPaddingTop() / 2, moveRight - one.getPaddingRight() / 2, moveBottom - one.getPaddingBottom() / 2);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(one.getWidth(), one.getHeight());
                        layoutParams.setMargins(moveLeft, moveTop, moveRight, moveBottom);
                        one.setLayoutParams(new FrameLayout.LayoutParams(layoutParams));
                        one.invalidate();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (buttonShadowView != null) {
                            buttonShadowView.setVisibility(View.GONE);
                        }
                        one.setVisibility(View.VISIBLE);
                        break;
                }
                return true;

            }
        });
        two.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    private ButtonShadowView buttonShadowView;

    //画一个button虚影
    private void drawShadow(int moveLeft, int moveTop, int moveRight, int moveBottom) {
        if (buttonShadowView == null) {
            buttonShadowView = new ButtonShadowView(this);
            buttonShadowView.setValue(one.getText().toString());
            buttonShadowView.setArea(one.getWidth(), one.getHeight());
            buttonShadowView.setParams(moveLeft, moveTop, moveRight, moveBottom);
            buttonShadowView.invalidate();
            container.addView(buttonShadowView);
        } else {
            buttonShadowView.setValue(one.getText().toString());
            buttonShadowView.setParams(moveLeft, moveTop, moveRight, moveBottom);
            buttonShadowView.invalidate();
        }
    }

    public static int getVirtualBarHeigh(Context context) {
        View decorView = ((Activity) context).getWindow().getDecorView();
        Display defaultDisplay = ((Activity) context).getWindow().getWindowManager().getDefaultDisplay();
        int height = decorView.getHeight();
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        int top = rect.top;
        return height - top - defaultDisplay.getHeight();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonOne:
                break;
            case R.id.buttonTwo:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
        }
    }
}
