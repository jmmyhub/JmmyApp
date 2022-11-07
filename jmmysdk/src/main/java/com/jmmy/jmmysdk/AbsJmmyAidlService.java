package com.jmmy.jmmysdk;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public abstract class AbsJmmyAidlService extends Service {
    private static final String TAG = "AbJmmyAidlService";
    public AbsJmmyAidlService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        MyBinder iBinder = new MyBinder();
        return iBinder;
    }

    public abstract void setCountValue(int value);

    public abstract int getCountValue();

    private class MyBinder extends IJmmyAidlInterface.Stub{

        @Override
        public void setCount(int value) throws RemoteException {
            setCountValue(value);
        }

        @Override
        public int getCount() throws RemoteException {
            return getCountValue();
        }

        @Override
        public void registerCallBack() throws RemoteException {

        }

        @Override
        public void unregisterCallBack() throws RemoteException {

        }
    }
}