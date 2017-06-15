package com.example.hzh.observedemo.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hzh.observedemo.R;
import com.example.hzh.observedemo.utils.SharedPerfrenceUtils;
import com.example.hzh.observedemo.utils.UIOpenhelper;

/**
 * Created by HZH on 2016/4/14.
 */
public class LaunchActivity extends BaseActivity {
    private ViewPager viewPager;
    private ImageView icon, icon2;
    private int[] images;
    private String[] titles;
    private TextView pageTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        SharedPerfrenceUtils.getInstance().saveLaunched("launch", true);
        initConfig();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        icon = (ImageView) findViewById(R.id.icons);
        icon2 = (ImageView) findViewById(R.id.icons2);
        pageTitle = (TextView) findViewById(R.id.pagetitle);
        viewPager.setAdapter(new pagerAdapter());
        viewPager.setPageMargin(0);
        viewPager.setOffscreenPageLimit(1);
        pageTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem() == titles.length - 1) {
                    if (SharedPerfrenceUtils.getInstance().isLaunched("launch") && SharedPerfrenceUtils.getInstance().isLaunched("login")) {
                        UIOpenhelper.ToOther(LaunchActivity.this, MainActivity.class);
                    } else {
                        UIOpenhelper.ToOther(LaunchActivity.this, LoginActivity.class);
                    }

                }
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                pageTitle.setText(titles[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE || state == ViewPager.SCROLL_STATE_SETTLING) {
                    final ImageView fadeInImage;
                    final ImageView fadeOutImage;
                    icon2.setVisibility(View.VISIBLE);
                    fadeInImage = icon;
                    fadeOutImage = icon2;
                    fadeInImage.clearAnimation();
                    fadeOutImage.clearAnimation();

                    Animation animation = AnimationUtils.loadAnimation(LaunchActivity.this, R.anim.fade_in);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            fadeInImage.setImageResource(images[viewPager.getCurrentItem()]);

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    Animation animation1 = AnimationUtils.loadAnimation(LaunchActivity.this, R.anim.fade_out);
                    animation1.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            fadeOutImage.setImageResource(images[viewPager.getCurrentItem()]);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    fadeInImage.startAnimation(animation);
                    fadeOutImage.startAnimation(animation1);
                }
            }
        });
    }

    private void initConfig() {
        images = new int[]{
                R.mipmap.intro1, R.mipmap.intro2, R.mipmap.intro3};
        titles = new String[]{
                "页面一", "页面二", "页面三"
        };
    }

    private class pagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(container.getContext(), R.layout.layout_item_title, null);
            TextView title = (TextView) view.findViewById(R.id.title);
            title.setText(titles[position]);
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SharedPerfrenceUtils.getInstance().isLaunched("launch")) {
                        UIOpenhelper.ToOther(LaunchActivity.this, MainActivity.class);
                    } else {
                        UIOpenhelper.ToOther(LaunchActivity.this, LoginActivity.class);
                    }
                }
            });
            container.addView(view);
            return container;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
        }
    }
}
