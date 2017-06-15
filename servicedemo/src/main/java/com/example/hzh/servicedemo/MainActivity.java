package com.example.hzh.servicedemo;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressDialog mProgressDialog;
    private TextView mTextView;
    private BroadcastReceiver mReceiver;
    private IntentFilter mIntentFilter;
    private ClickCallBackAidl clickCallBackAidl = new ClickCallBackAidl.Stub() {
        @Override
        public String ClickCaback(String msg) throws RemoteException {
            Log.i("Aidl回调信息------", msg);
            return null;
        }
    };
    private PersonEntity personEntity;
    private IBinder mToken = new Binder();
    private boolean mIsJoin = false;

    IRemoteService remoteService;
    private boolean serviceReady;

    private void toggleJoin() {
        if (!isServiceReady()) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mTextView = (TextView) findViewById(R.id.text_view);
        mTextView.setOnClickListener(this);
    }

    private DeliverAidl deliverAidl;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //deliverAidl = DeliverAidl.Stub.asInterface(service);
            remoteService = IRemoteService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //deliverAidl = null;
            remoteService = null;
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, AidlService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    private void update(String string) {
        mProgressDialog.dismiss();
        mTextView.setText(string);
    }

    @Override
    public void onClick(View v) {
        try {
            personEntity = new PersonEntity();
            personEntity.setAge(18);
            personEntity.setName("张三");
            personEntity.setSex(1);
            deliverAidl.deliverCallBack(clickCallBackAidl);
            deliverAidl.deliverEntity(personEntity);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isServiceReady() {
        if (remoteService != null) {
            return true;
        } else {
            return false;
        }
    }

    class MyServiceRecevier extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            update(intent.getExtras().getString(MyService.MSG_KEY));
        }

    }

}
