package com.jmmy.jmmyapp;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

public class AbJmmyAidlService extends Service {
    public AbJmmyAidlService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new IJmmyAidlInterface();

    }

    private class IJmmyAidlInterface extends IMyAidlInterface.Stub{

        @Override
        public void setCount(int value) throws RemoteException {

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
}