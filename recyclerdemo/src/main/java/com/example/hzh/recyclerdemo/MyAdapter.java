package com.example.hzh.recyclerdemo;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HZH on 2015/12/1.
 */
public class MyAdapter extends RecyclerView.Adapter {
    private List<ItemEntity> mResult;
    private Context mContext;
    public static boolean BOTTOMFLAG = false;
    private View mFootView = null;
    private View mEmptyView = null;
    public static boolean IsComplete = false;

    public MyAdapter(Context context) {
        this.mContext = context;
    }

    public void BindData(List<ItemEntity> result) {
        this.mResult = result;
        notifyDataSetChanged();
    }

    public void AddData(List<ItemEntity> moreResult) {
        mResult.addAll(moreResult);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footview, parent, false);
            holder = new FootViewHolder(view);
            if (!IsComplete && BOTTOMFLAG) {
                holder.itemView.setVisibility(View.VISIBLE);
            } else {
                holder.itemView.setVisibility(View.GONE);

            }
        } else if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_footview, parent, false);
            holder = new EmptyViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            holder = new ItemHolder(view);
        }
        //   ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,AbsListView.LayoutParams.WRAP_CONTENT);
        //view.setLayoutParams(new ViewGroup.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 2) {
            if (position < getItemCount() && (mFootView != null ? position <= mResult.size() : position < mResult.size()) && (mFootView != null ? position > 0 : true)) {
            }
        } else if (getItemViewType(position) == 0 && position < getItemCount() && (mFootView != null ? position <= mResult.size() : position < mResult.size()) && (mFootView != null ? position > 0 : true)) {
            ItemHolder itemHolder = (ItemHolder) holder;
            ItemEntity itemEntity = mResult.get(position);
            Typeface typeFace =Typeface.createFromAsset(mContext.getAssets(), "kt.ttf");
            itemHolder.tv.setText(itemEntity.getName());
            itemHolder.tv.setTypeface(typeFace);
            itemHolder.iv.setImageDrawable(itemEntity.getDrawable());
            itemHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } else if (getItemViewType(position) == 3 && mEmptyView != null) {

        }
    }

    @Override
    public int getItemCount() {
        int footer = 0;
        if (mFootView != null) {
            footer++;
        }
        if (mEmptyView != null) {
            footer++;
        }
        //if (mFootView!=null&&!BOTTOMFLAG)footer++;
        return mResult.size() + footer;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView iv;
        private TextView tv;

        public ItemHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.cardview);
            iv = (ImageView) view.findViewById(R.id.iv);
            tv = (TextView) view.findViewById(R.id.tv);
        }
    }

    public class FootViewHolder extends RecyclerView.ViewHolder {
        public FootViewHolder(View view) {
            super(view);
        }
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View view) {
            super(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mResult.size() - 1 && mFootView != null) {
            // if (BOTTOMFLAG) {
            //   return 2;
            //}
            return 2;
        } else if (position == mResult.size() - 1 && mEmptyView != null && !BOTTOMFLAG) {
            return 3;
        }
        return 0;
    }

    public void SetFootView(View footView) {
        if (footView != null) {
            mFootView = footView;
        }
    }

    public void SetEmptyView(View enptyView) {
        if (enptyView != null) {
            mEmptyView = enptyView;
        }
    }

}
