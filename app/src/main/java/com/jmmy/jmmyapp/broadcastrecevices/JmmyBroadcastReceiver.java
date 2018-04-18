package com.jmmy.jmmyapp.broadcastrecevices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by jmmy on 2017/12/15.
 */

public class JmmyBroadcastReceiver extends BroadcastReceiver {
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
        if (intent.getAction() != null && intent.getAction().equals(IntentAction)){
            Log.i("wjm","nihao ............");
        }
    }
}
