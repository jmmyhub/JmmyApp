package com.jmmy.jmmyapp.Utils;

import android.util.Log;

public class LogUtils {
    public static String TAG = "JmmyApp_";

    public static void i(String tag,String message){
        Log.i(TAG+"#"+tag,message);
    }
}
