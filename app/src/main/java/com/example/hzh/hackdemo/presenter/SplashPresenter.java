package com.example.hzh.hackdemo.presenter;

import com.example.hzh.hackdemo.impl.ConnectionStatus;
import com.example.hzh.hackdemo.model.IConnectionStatus;
import com.example.hzh.hackdemo.view.ISplashView;

/**
 * Created by HZH on 2015/11/30.
 */
public class SplashPresenter {
    private IConnectionStatus mIConnectionStatus;
    private ISplashView mISplashView;

    public SplashPresenter() {
        this(new ConnectionStatus());
    }

    public SplashPresenter(IConnectionStatus iConnectionStatus) {
        mIConnectionStatus = iConnectionStatus;
    }

    public void setView(ISplashView view) {
        this.mISplashView = view;
    }

    public ISplashView getView() {
        return mISplashView;
    }

    public void didFinishLoading() {
        ISplashView view = getView();
        if (mIConnectionStatus.isOnline()) {
            mISplashView.hidePreogress();
            mISplashView.moveToMainView();
        } else {
            mISplashView.hidePreogress();
            mISplashView.showNoInetErrorMsg();
        }

    }


}
