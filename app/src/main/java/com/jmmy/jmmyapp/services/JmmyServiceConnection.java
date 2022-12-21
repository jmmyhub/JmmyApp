package com.jmmy.jmmyapp.services;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.jmmy.jmmyapp.utils.LogUtils;
import com.jmmy.jmmysdk.IJmmyAidlInterface;

public class JmmyServiceConnection implements ServiceConnection {
    private static final String TAG = "JmmyServiceConnection";
    private IJmmyAidlInterface iJmmyAidlInterface;
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

    public IJmmyAidlInterface getJmmyAidlInterface() {
        return iJmmyAidlInterface;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        LogUtils.i(TAG, "onServiceConnected name:" + name);
        iJmmyAidlInterface = IJmmyAidlInterface.Stub.asInterface(service);
        if (iJmmyAidlInterface != null) {
            try {
                LogUtils.i(TAG, "onServiceConnected " + iJmmyAidlInterface.getCount());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        LogUtils.i(TAG, "onServiceDisconnected name:" + name);
    }
}
