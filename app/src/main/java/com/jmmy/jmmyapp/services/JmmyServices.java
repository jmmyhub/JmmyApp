package com.jmmy.jmmyapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.jmmy.jmmyapp.utils.LogUtils;
import com.jmmy.jmmyapp.utils.MyInterfaceImpl;

/**
 * Created by jmmy on 2017/12/15.
 */

public class JmmyServices extends Service {
    private static final String TAG = "JmmyServices";
    @Override
    public void onCreate() {
        LogUtils.i(TAG, "onCreate");
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.i(TAG, "onBind intent:" + intent);
        return new MyInterfaceImpl();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
