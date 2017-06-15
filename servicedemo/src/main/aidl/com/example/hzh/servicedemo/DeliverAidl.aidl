// DeliverAidl.aidl
package com.example.hzh.servicedemo;
import com.example.hzh.servicedemo.PersonEntity;
import com.example.hzh.servicedemo.ClickCallBackAidl;

// Declare any non-default types here with import statements

interface DeliverAidl {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     void deliverEntity(in PersonEntity entity);
     //注册监听回调事件
     void deliverCallBack(ClickCallBackAidl aidl);
     //销毁回调事件
     void destoryCallBack(String packName,ClickCallBackAidl aidl);
}
