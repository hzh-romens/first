package com.example.hzh.drawabledemo;

import android.util.Log;

/**
 * Created by Lanxumit on 2017/6/10.
 */

public class ChildDemo extends FinalDemo {
    @Override
    public void logOut() {
        super.logOut();
        Log.i("子类调用","是");
    }
}
