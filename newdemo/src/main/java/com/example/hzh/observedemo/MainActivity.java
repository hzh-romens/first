package com.example.hzh.observedemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.hzh.observedemo.activity.BaseActivity;
import com.example.hzh.observedemo.fragment.MainFragment;
import com.example.hzh.observedemo.fragment.ManagerFragment;
import com.example.hzh.observedemo.fragment.Otherfragment;
import com.example.hzh.observedemo.fragment.TestFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private ViewPager viewPager;
    private RadioGroup tabs;
    private List<Fragment> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabs = (RadioGroup) findViewById(R.id.tabContainer);
        initFragment();
        viewPager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                RadioButton childAt = (RadioButton) tabs.getChildAt(position);
                for (int i = 0; i < tabs.getChildCount(); i++) {
                    if (position == i) {
                        childAt.setChecked(true);
                    }
                }
            }


            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        for (int i = 0; i < tabs.getChildCount(); i++) {
            final int currentItem = i;
            tabs.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(currentItem);
                }
            });
        }


    }

    //初始化Fragment;
    private void initFragment() {
        fragments = new ArrayList<Fragment>();
        fragments.add(new MainFragment());
        fragments.add(new TestFragment());
        fragments.add(new Otherfragment());
        fragments.add(new ManagerFragment());

    }

    private class MainFragmentAdapter extends FragmentPagerAdapter {

        public MainFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
