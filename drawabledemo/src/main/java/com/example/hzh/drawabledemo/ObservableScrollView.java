package com.example.hzh.drawabledemo;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ScrollView;

import java.util.List;

/**
 * Created by HZH on 2016/1/6.
 */
public class ObservableScrollView extends ScrollView implements Scrollable {
    private int mPrevScrollY;
    private int mScrollY;
    private ObservableScrollViewCallbacks mCallbacks;
    private List<ObservableScrollViewCallbacks> mCallbackCollection;
    private ScrollState mScrollState;
    private boolean mFirstScroll;
    private boolean mDragging;
    private boolean mPrevMoveEvent;
    private ViewGroup mTouchInterceptionViewGroup;


    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setScrollViewCallbacks(ObservableScrollViewCallbacks listener) {

    }

    @Override
    public void addScrollViewCallbacks(ObservableScrollViewCallbacks listener) {

    }

    @Override
    public void removeScrollViewCallbacks(ObservableScrollViewCallbacks listener) {

    }

    @Override
    public void clearScrollViewCallbacks() {

    }

    @Override
    public void scrollVerticallyTo(int y) {

    }

    @Override
    public int getCurrentScrollY() {
        return 0;
    }

    @Override
    public void setTouchInterceptionViewGroup(ViewGroup viewGroup) {

    }

    static class SaveState extends BaseSavedState {
        int preScrollY;
        int scrollY;

        SaveState(Parcelable superState) {
            super(superState);
        }

        public SaveState(Parcel source) {
            super(source);
            preScrollY = source.readInt();
            scrollY = source.readInt();
        }
    }
}
