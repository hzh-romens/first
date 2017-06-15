// IRemoteService.aidl
package com.example.hzh.servicedemo;

// Declare any non-default types here with import statements

interface IRemoteService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
   void join(IBinder token,String name);
   void leave(IBinder token,String name);
   List<String> getParticipators();
}
