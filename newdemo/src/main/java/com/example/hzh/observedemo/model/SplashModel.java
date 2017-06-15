package com.example.hzh.observedemo.model;

import com.example.hzh.observedemo.MyApplication;
import com.example.hzh.observedemo.R;
import com.example.hzh.observedemo.entitiy.ResultBean;
import com.example.hzh.observedemo.utils.NetUtils;
import com.example.hzh.observedemo.utils.SharedPerfrenceUtils;

/**
 * Created by Lanxumit on 2016/11/1.
 */
public class SplashModel implements ISplashModel {
    @Override
    public void getData(CallBack callBack) {
        //如果网络有错
        if (!NetUtils.getNetStatus()) {
            callBack.onResult(new ResultBean(-1, MyApplication.context.getResources().getString(R.string.netError)));
            return;
        }
        if (SharedPerfrenceUtils.getInstance().isLaunched("launch") && SharedPerfrenceUtils.getInstance().isLaunched("login")) {
            callBack.onResult(new ResultBean(2, "main"));
        } else if (!SharedPerfrenceUtils.getInstance().isLaunched("launch")) {
            callBack.onResult(new ResultBean(1, "launch"));
        } else if (SharedPerfrenceUtils.getInstance().isLaunched("launch") && !SharedPerfrenceUtils.getInstance().isLaunched("login")) {
            callBack.onResult(new ResultBean(3, "login"));
        }
        return;

    }
}
