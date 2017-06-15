package com.example.hzh.drawabledemo;

import android.util.Log;

/**
 * Created by Lanxumit on 2017/6/10.
 */

public class FinalDemo {
    public void logOut(){
        Log.i("父类调用----", "是");
    }
    final void test() {
        Log.i("最先调用----", "是");
    }
}
