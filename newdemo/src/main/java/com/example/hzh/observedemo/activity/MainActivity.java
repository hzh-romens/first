package com.example.hzh.observedemo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.hzh.observedemo.R;
import com.example.hzh.observedemo.compoents.HomeTabsCell;
import com.example.hzh.observedemo.fragment.MainFragment;
import com.example.hzh.observedemo.fragment.ManagerFragment;
import com.example.hzh.observedemo.fragment.Otherfragment;
import com.example.hzh.observedemo.fragment.TestFragment;
import com.mikepenz.materialize.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private ViewPager viewPager;
    private RadioGroup tabs;
    private List<Fragment> fragments;
    private FrameLayout container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        container = (FrameLayout) findViewById(R.id.container);
        HomeTabsCell homeTabsCell = new HomeTabsCell(this);
        homeTabsCell.setViewPager(viewPager);
        homeTabsCell.addItem("一", R.mipmap.ic_tab_fl, 0);
        homeTabsCell.addItem("二", R.mipmap.ic_tab_fx, 1);
        homeTabsCell.addItem("三", R.mipmap.ic_tab_fl, 2);
        homeTabsCell.addItem("四", R.mipmap.ic_tab_fx, 3);
        homeTabsCell.setChecked(0);

        container.addView(homeTabsCell, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) UIUtils.convertDpToPixel(48, this)));
        initFragment();
        viewPager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager()));
        //测试提交代码
    }

    //初始化Fragment;
    private void initFragment() {
        fragments = new ArrayList<Fragment>();
        fragments.add(new MainFragment());
        fragments.add(new TestFragment());
        fragments.add(new Otherfragment());
        fragments.add(new ManagerFragment());
        //检测merge测试
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
