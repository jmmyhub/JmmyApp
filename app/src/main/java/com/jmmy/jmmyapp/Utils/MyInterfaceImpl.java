package com.jmmy.jmmyapp.Utils;

import android.os.RemoteException;

import com.jmmy.jmmyapp.IMyAidlInterface;

public class MyInterfaceImpl extends IMyAidlInterface.Stub {
    private static final String TAG = "MyInterfaceImpl";
    @Override
    public void setCount(int value) throws RemoteException {
        LogUtils.i(TAG, "setCount value: " + value);
    }

    @Override
    public void getCount() throws RemoteException {

    }

    @Override
    public void registerCallBack() throws RemoteException {

    }

    @Override
    public void unregisterCallBack() throws RemoteException {

    }
}
