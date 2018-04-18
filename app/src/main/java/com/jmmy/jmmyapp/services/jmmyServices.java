package com.jmmy.jmmyapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

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
        Log.i("wjm","jmmyService onStart" );
        super.onStart(intent, startId);
    }

}
