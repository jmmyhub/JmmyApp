package com.jmmy.jmmyapp.Utils;

import android.util.Log;

public class LogUtils {
    private static String TAG = "JmmyApp";

    public static void i(String tag,String message){
        Log.i(TAG+"#"+tag,message);
    }
}
