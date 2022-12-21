package com.jmmy.jmmyapp.utils;

import android.os.RemoteException;

import com.jmmy.jmmysdk.IJmmyAidlInterface;

public class MyInterfaceImpl extends IJmmyAidlInterface.Stub{
    private static final String TAG = "MyInterfaceImpl";
    @Override
    public void setCount(int value) throws RemoteException {
        LogUtils.i(TAG, "setCount value: " + value);
    }

    @Override
    public int getCount() throws RemoteException {
        return 10;
    }

    @Override
    public void registerCallBack() throws RemoteException {

    }

    @Override
    public void unregisterCallBack() throws RemoteException {

    }
}
