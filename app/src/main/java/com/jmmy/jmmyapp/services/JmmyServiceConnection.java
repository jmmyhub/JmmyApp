package com.jmmy.jmmyapp.services;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.jmmy.jmmyapp.IMyAidlInterface;
import com.jmmy.jmmyapp.Utils.LogUtils;

public class JmmyServiceConnection implements ServiceConnection {
    private static final String TAG = "JmmyServiceConnection";
    private IMyAidlInterface aidlInterface;
    private static JmmyServiceConnection instance;
    private static final Object LOCK_INSTANCE = new Object();

    public static JmmyServiceConnection getInstance(){
        synchronized (LOCK_INSTANCE) {
            if (instance == null) {
                instance = new JmmyServiceConnection();
            }
            return instance;
        }
    }

    public IMyAidlInterface getAidlInterface() {
        return aidlInterface;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        LogUtils.i(TAG, "onServiceConnected name:" + name);
        aidlInterface = IMyAidlInterface.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        LogUtils.i(TAG, "onServiceDisconnected name:" + name);
    }
}
