package com.example.lanxumit.myapplication;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {

    public MainActivityFragment() {
    }

    private TextView refresh;
    private ImageView image;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_main, container, false);
        refresh = (TextView) inflate.findViewById(R.id.refresh);
        image = (ImageView) inflate.findViewById(R.id.image);
        image.setOnClickListener(this);
        refresh.setOnClickListener(this);
        return inflate;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.refresh:
                refresh.setVisibility(View.GONE);
                image.setVisibility(View.VISIBLE);
                startAnimation();
                break;
            case R.id.image:
                break;
        }
    }

    private void startAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(image, "rotation", 0f, 360f);
        animator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                refresh.setVisibility(View.VISIBLE);
                image.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.setDuration(3000);
        animator.start();
    }
}
