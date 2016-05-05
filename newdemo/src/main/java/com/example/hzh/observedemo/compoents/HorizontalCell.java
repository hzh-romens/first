package com.example.hzh.observedemo.compoents;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by pc on 2016/4/28.
 */
public class HorizontalCell extends FrameLayout {
    private RecyclerView horizontalListView;

    public HorizontalCell(Context context) {
        super(context);
        horizontalListView = new RecyclerView(context);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalListView.setLayoutManager(manager);

    }
    private class HorizontalAdapter extends RecyclerView.Adapter{
        public HorizontalAdapter(){

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
