package com.example.hzh.observedemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hzh.observedemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2016/4/26.
 */
public class ActionBarsActivity extends BaseActivity {
    private ListView listView;
    private Toolbar toolbar;
    private ActionBarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionbar);
        listView = (ListView) findViewById(R.id.listview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.md_red_300));
        setSupportActionBar(toolbar);
        adapter = new ActionBarAdapter();
        initData();
        listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                previouPosition = view.getFirstVisiblePosition();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem > previouPosition) {
                    //向上滑动
                    if (firstVisibleItem != previouPosition) {
                        int height = view.getChildAt(0).getHeight();
                        int alpha = (firstVisibleItem - previouPosition) * height;
                        if (alpha >= toolbar.getHeight()) {
                            toolbar.getBackground().setAlpha(0);
                        } else {
                            toolbar.getBackground().setAlpha(alpha);
                        }
                    }
                } else {
                    //向下滑动
                    if (view != null && view.getChildAt(0) != null) {
                        int height = view.getChildAt(0).getHeight();
                        int alpha = (previouPosition - firstVisibleItem) * height;
                        if (alpha >= toolbar.getHeight() || firstVisibleItem == 0) {
                            toolbar.setBackgroundColor(getResources().getColor(R.color.md_red_300));
                        } else {
                            toolbar.getBackground().setAlpha(alpha);
                        }
                    }
                }
            }
        });
    }

    private int previouPosition;
    private List<String> result = new ArrayList<String>();

    private void initData() {
        for (int i = 0; i < 20; i++) {
            result.add("item" + i);
        }
    }

    private class ActionBarAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return result == null ? 0 : result.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView item = new TextView(ActionBarsActivity.this);
            item.setPadding(0, 16, 0, 16);
            item.setTextSize(16);
            item.setTextColor(Color.BLACK);
            item.setText(result.get(position));
            item.setGravity(Gravity.CENTER);
            item.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return item;
        }
    }
}
