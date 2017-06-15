package com.example.hzh.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Lanxumit on 2017/5/2.
 */

public class AidlService extends Service {
    private ClickCallBackAidl clickCallBackAidl;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

    DeliverAidl.Stub stub = new DeliverAidl.Stub() {
        @Override
        public void deliverEntity(PersonEntity entity) throws RemoteException {
            clickCallBackAidl.ClickCaback(entity.getName());
        }

        @Override
        public void deliverCallBack(ClickCallBackAidl aidl) throws RemoteException {
            clickCallBackAidl = aidl;
        }

        @Override
        public void destoryCallBack(String packageName,ClickCallBackAidl aidl) throws RemoteException {

        }
    };
}
