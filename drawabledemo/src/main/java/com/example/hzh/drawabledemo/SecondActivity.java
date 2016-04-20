package com.example.hzh.drawabledemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HZH on 2016/1/6.
 */
public class SecondActivity extends AppCompatActivity {
    private ListView listView;
    private ImageView imageView;
    private TestAdapter testAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        listView = (ListView) findViewById(R.id.list);
        imageView = (ImageView) findViewById(R.id.test);
        testAdapter = new TestAdapter(this);
        getData();
        testAdapter.bindData(result);
        listView.setAdapter(testAdapter);

    }

    private List<String> result;

    public void getData() {
        result = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            result.add("item" + i);
        }
    }

    public class TestAdapter extends BaseAdapter {
        private Context mContext;
        private List<String> mResult;
        private int mHeight;

        public TestAdapter(Context context) {
            this.mContext = context;
        }

        public void bindData(List<String> result) {
            this.mResult = result;
            notifyDataSetChanged();
        }

        public void setFirstItemHeight(int height) {
            this.mHeight = height;
            notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return 1;
            } else {
                return 2;
            }
        }

        @Override
        public int getCount() {
            return mResult.size() == 0 ? 0 : mResult.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (getItemViewType(position) == 1) {
                if (convertView == null) {
                    convertView = new EmptyCell(mContext);
                }
                EmptyCell emptyCell = (EmptyCell) convertView;
                emptyCell.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeight));

            } else {
                if (convertView == null) {
                    convertView = new TextViewCell(mContext);
                }
                TextViewCell textViewCell = (TextViewCell) convertView;
                textViewCell.setValue(mResult.get(position));
            }
            return convertView;
        }
    }
}
