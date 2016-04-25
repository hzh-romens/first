package com.example.hzh.observedemo.activity;

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
public class ActionBarsActivity extends BaseActivity{
    private ListView listView;
    private Toolbar toolbar;
    private ActionBarAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionbar);
        listView= (ListView) findViewById(R.id.listview);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adapter=new ActionBarAdapter();
        initData();
        listView.setAdapter(adapter);
        listView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //根据上下滑动的距离实现背景颜色的渐变或者其他处理
               // toolbar.getBackground().setAlpha();
            }
        });
    }
    private List<String> result=new ArrayList<String>();

    private void initData() {
        for (int i=0;i<20;i++){
            result.add("item"+i);
        }
    }

    private class ActionBarAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return result==null?0:result.size();
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
            TextView item=new TextView(ActionBarsActivity.this);
            item.setTextSize(16);
            item.setText(result.get(position));
            item.setGravity(Gravity.CENTER);
            item.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return item;
        }
    }
}
