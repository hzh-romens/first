package com.example.hzh.servicedemo;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;
    private TextView mTextView;
    private BroadcastReceiver mReceiver;
    private IntentFilter mIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mTextView= (TextView) findViewById(R.id.text_view);
        mProgressDialog=ProgressDialog.show(this,"Loading","Please wait");
        mProgressDialog.show();
        mReceiver=new MyServiceRecevier();
        mIntentFilter=new IntentFilter(MyService.ACTION);
        startService(new Intent(this,MyService.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver,mIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

    private void update(String string) {
        mProgressDialog.dismiss();
        mTextView.setText(string);
    }
    class MyServiceRecevier extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            update(intent.getExtras().getString(MyService.MSG_KEY));
        }


    }

}
