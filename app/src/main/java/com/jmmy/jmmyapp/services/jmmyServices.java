package com.jmmy.jmmyapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by jmmy on 2017/12/15.
 */

public class jmmyServices extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }
}
