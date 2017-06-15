package com.example.hzh.observedemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.hzh.observedemo.R;
import com.example.hzh.observedemo.compoents.HomeTabsCell;
import com.mikepenz.materialize.util.UIUtils;

/**
 * Created by pc on 2016/4/25.
 */
public class TestFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private FrameLayout frameLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), R.layout.fragment_news, null);
        frameLayout = (FrameLayout) inflate.findViewById(R.id.container);
        viewPager = (ViewPager) inflate.findViewById(R.id.viewpager);
        HomeTabsCell homeTabsCell = new HomeTabsCell(getActivity());
        homeTabsCell.setViewPager(viewPager);
      //  HomeTabCell cell = new HomeTabCell(getActivity(), "一", R.mipmap.ic_tab_fl);
       // HomeTabCell cell2 = new HomeTabCell(getActivity(), "二", R.mipmap.ic_tab_fx);
       // homeTabsCell.addItem(cell);
       // homeTabsCell.addItem(cell2);
        frameLayout.addView(homeTabsCell, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) UIUtils.convertDpToPixel(48, getActivity())));
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
