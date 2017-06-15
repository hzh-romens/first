package com.example.hzh.observedemo.compoents;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.hzh.observedemo.R;
import com.example.hzh.observedemo.entitiy.ViewPagerEntity;
import com.example.hzh.observedemo.utils.OkHttpClientManager;

import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by pc on 2016/4/28.
 */
public class ViewPagerCell extends FrameLayout {
    private int currentPosition;
    private ViewPager viewPager;
    private List<ViewPagerEntity> result;
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

    public ViewPagerCell(Context context) {
        super(context);
        final View view = View.inflate(context, R.layout.layout_item_viewpager, null);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (currentPosition == result.size() - 1) {
                    currentPosition = 0;
                    viewPager.setCurrentItem(currentPosition);
                } else {
                    viewPager.setCurrentItem(currentPosition + 1);
                }
            }
        };
        executorService.schedule(timerTask, 2000, TimeUnit.MILLISECONDS);
    }

    public void setValue(List<ViewPagerEntity> result) {
        this.result = result;
    }


    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return result.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            currentPosition = position;
            View image = View.inflate(container.getContext(), R.layout.layout_item_image, null);
            ImageView imageView = (ImageView) image.findViewById(R.id.image);
            OkHttpClientManager.displayImage(imageView, result.get(position).getImageUrl());
            return image;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
