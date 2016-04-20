package com.example.hzh.recyclerdemo;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<ItemEntity> result;
    private SwipeRefreshLayout refreshLayout;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    break;
                case 2:
                    List<ItemEntity> itemEntities = (List<ItemEntity>) msg.obj;
                    myAdapter.AddData(itemEntities);
                    myAdapter.IsComplete=true;
                    myAdapter.SetEmptyView(LayoutInflater.from(MainActivity.this).inflate(R.layout.item_empty_footview, null));
                    break;
                case 0:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.RefreshLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.addItemDecoration(new ListDivider(this,ListDivider.VERTICAL_LIST));
//        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//                super.onDraw(c, parent, state);
//            }
//
//            @Override
//            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
//                super.onDrawOver(c, parent, state);
//                Paint paint = new Paint();
//                paint.setColor(getResources().getColor(android.R.color.holo_green_light));
//                int childCount = parent.getChildCount();
//                for (int i = 0; i < childCount; i++) {
//                    View childAt = parent.getChildAt(i);
//                    float x = childAt.getWidth() + childAt.getX();
//                    float y = childAt.getHeight() + childAt.getY();
//                    c.drawLine(childAt.getX(),y,x,y,paint);
//                }
//            }
//
//            @Override
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                outRect.top = 1;
//                super.getItemOffsets(outRect, view, parent, state);
//            }
//        });
        refreshLayout.setRefreshing(true);
        initData();
        myAdapter = new MyAdapter(this);
        myAdapter.BindData(result);
        recyclerView.setAdapter(myAdapter);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                initData();
            }
        });
        refreshLayout.setColorSchemeResources(R.color.md_green_500, R.color.md_red_500, R.color.md_blue_500);
        refreshLayout.setProgressViewOffset(false, 0, 48);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastCompletelyVisibleItemPosition = manager.findLastCompletelyVisibleItemPosition();
                    int itemCount = manager.getItemCount();
                    if (lastCompletelyVisibleItemPosition == itemCount - 1 && isSlidingToLast) {
                        //到达底部
                        myAdapter.SetFootView(LayoutInflater.from(MainActivity.this).inflate(R.layout.item_footview,null));
                        MyAdapter.BOTTOMFLAG=true;
                        myAdapter.IsComplete=false;
                        //加载更多数据
                        AddMoreData();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager1 = (LinearLayoutManager) recyclerView.getLayoutManager();
                int orientation = layoutManager1.getOrientation();
                if (orientation == LinearLayoutManager.VERTICAL) {
                    //竖直滚动
                    if (dy > 0) {
                        //向上滚动
                        isSlidingToLast = true;
                    } else {
                        isSlidingToLast = false;
                    }
                } else {
                    //水平滚动
                    if (dx > 0) {
                        //向右滚动
                        isSlidingToLast = true;
                    } else {
                        isSlidingToLast = false;
                    }
                }
            }
        });
    }

    private void AddMoreData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<ItemEntity> itemEntities = new ArrayList<>();
                Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
                mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                List<ResolveInfo> resolveInfos = getPackageManager().queryIntentActivities(mainIntent, 0);
                //for (int i = 0; i < resolveInfos.size(); i++) {
                ResolveInfo resolveInfo = resolveInfos.get(0);
                ItemEntity itemEntity = new ItemEntity();
                itemEntity.setName(resolveInfo.activityInfo.applicationInfo.loadLabel(getPackageManager()).toString());
                itemEntity.setDrawable(resolveInfo.loadIcon(getPackageManager()));
                itemEntities.add(itemEntity);
                handler.sendMessage(handler.obtainMessage(2,itemEntities));

            }
        },3000);



    }

    private void initData() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                result = new ArrayList<ItemEntity>();
                Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
                mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                List<ResolveInfo> resolveInfos = getPackageManager().queryIntentActivities(mainIntent, 0);
                for (int i = 0; i < resolveInfos.size(); i++) {
                    ResolveInfo resolveInfo = resolveInfos.get(i);
                    ItemEntity itemEntity = new ItemEntity();
                    itemEntity.setName(resolveInfo.activityInfo.applicationInfo.loadLabel(getPackageManager()).toString());
                    itemEntity.setDrawable(resolveInfo.loadIcon(getPackageManager()));

                    result.add(itemEntity);
                }
                refreshLayout.setRefreshing(false);
            }
        });


    }

}
