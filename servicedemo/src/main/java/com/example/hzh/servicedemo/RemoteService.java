package com.example.hzh.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lanxumit on 2017/5/3.
 */

public class RemoteService extends Service {
    private List<Client> mClients = new ArrayList<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

    private final IRemoteService.Stub stub = new IRemoteService.Stub() {
        @Override
        public void join(IBinder token, String name) throws RemoteException {
            //mClients.add(userName);'
            int client = findClient(token);
            if (client >= 0) {
                //已经运行了
                return;
            }
            Client client1 = new Client(token, name);
            token.linkToDeath(client1, 0);
            mClients.remove(client1);
        }

        @Override
        public void leave(IBinder token, String name) throws RemoteException {
            //mClients.remove(userName);
            int client = findClient(token);
            if (client < 0) {
                //已经去除了
                return;
            }
            Client client1 = mClients.get(client);
            mClients.remove(client1);
            //取消注册
            client1.mToken.unlinkToDeath(client1,0);
        }

        @Override
        public List<String> getParticipators() throws RemoteException {
            return null;
        }
    };

    //通过IBinder查找Client
    private int findClient(IBinder token) {
        for (int i = 0; i < mClients.size(); i++) {
            if (mClients.get(i).mToken == token) {
                return i;
            }
        }
        return -1;
    }

    private final class Client implements IBinder.DeathRecipient {
        public final IBinder mToken;
        public final String mName;

        public Client(IBinder token, String name) {
            mToken = token;
            mName = name;
        }

        @Override
        public void binderDied() {
            //客户端死掉，执行此回调
            int i = mClients.indexOf(this);
            if (i < 0) {
                return;
            }
            mClients.remove(this);
        }
    }
}

