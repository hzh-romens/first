package com.example.hzh.drawabledemo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by AUSU on 2016/4/20.
 */
public class AnimationActivity extends Activity{
    private Button click;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_view);
        initView();
        ValueAnimator animator=ValueAnimator.ofFloat(0f,1f);
        animator.setDuration(300);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                Log.d("TAG", "cuurent value is " + currentValue);
            }
        });
        animator.start();

        ValueAnimator valueAnimator2=ValueAnimator.ofFloat(0f,5f,3f,10f);
        valueAnimator2.setDuration(5000);
        valueAnimator2.start();

        ValueAnimator valueAnimator3=ValueAnimator.ofInt(0,100);
        valueAnimator3.setDuration(10000);
        valueAnimator3.start();

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void Alpha(View view){
        ObjectAnimator animator=ObjectAnimator.ofFloat(view,"alpha",1f,0f,1f);
        animator.setDuration(5000);
        animator.start();
    }

    public void Rotation(View view){
        ObjectAnimator animator=ObjectAnimator.ofFloat(view,"rotation",0,360f);
        animator.setDuration(5000);
        animator.start();
    }

    public void translationX(View view){
        float translationX = view.getTranslationX();
        ObjectAnimator animator=ObjectAnimator.ofFloat(view,"translationX",translationX,-500f,translationX);
        animator.setDuration(5000);
        animator.start();
    }
    public void ScaleY(View view){
        ObjectAnimator animator=ObjectAnimator.ofFloat(view,"scaleY",1f,3f,1f);
    }

    private void initView() {
        click= (Button) findViewById(R.id.click);
        textView= (TextView) findViewById(R.id.text);
    }
}
