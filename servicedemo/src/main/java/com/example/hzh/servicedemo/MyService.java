package com.example.hzh.servicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;

import java.util.Date;


/**
 * Created by HZH on 2015/11/30.
 */
public class MyService extends IntentService{
    public static final String ACTION = "com.manning.androidhacks.hack021.SERVICE_MSG";
    public static final String MSG_KEY = "MSG_KEY";
    private Handler handler=new Handler();

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyService(String name) {
        super(name);
    }
    public MyService(){
        super("MyService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SystemClock.sleep(5000L);
        handler.postDelayed(runnable,5000);

    }
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this,5000);
            Intent broadcast = new Intent();
            broadcast.setAction(ACTION);
            broadcast.putExtra(MSG_KEY, new Date().toGMTString());
            sendBroadcast(broadcast);
        }
    };
}
