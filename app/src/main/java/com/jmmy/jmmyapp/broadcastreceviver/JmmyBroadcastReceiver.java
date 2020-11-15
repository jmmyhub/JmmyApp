package com.jmmy.jmmyapp.broadcastreceviver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.jmmy.jmmyapp.Utils.LogUtils;

/**
 * Created by jmmy on 2017/12/15.
 */

public class JmmyBroadcastReceiver extends BroadcastReceiver {
    private String TAG = "JmmyBroadcastReceiver";
    private static JmmyBroadcastReceiver broadcastReceiver;
    private final static String IntentAction = "com.jmmy.app.broadcastrecevices";

    public static JmmyBroadcastReceiver getInstance() {
        if (broadcastReceiver == null){
            broadcastReceiver = new JmmyBroadcastReceiver();
        }
        return broadcastReceiver;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        //LogUtils.i(TAG,"action : " + intent.getAction());
        if (intent != null && intent.getAction() != null && intent.getAction().equals(IntentAction)){
            LogUtils.i(TAG,"onReceive  weijianming ............");
        }
    }

}
