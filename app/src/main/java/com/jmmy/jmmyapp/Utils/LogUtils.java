package com.jmmy.jmmyapp.Utils;

import android.util.Log;

public class LogUtils {
    public static String TAG = "JmmyApp_";

    public static void v(String tag,String message){
        Log.v(TAG+"#"+tag,message);
    }
    public static void d(String tag,String message){
        Log.d(TAG+"#"+tag,message);
    }
    public static void i(String tag,String message){
        Log.i(TAG+"#"+tag,message);
    }
    public static void w(String tag,String message){
        Log.w(TAG+"#"+tag,message);
    }public static void e(String tag,String message){
        Log.e(TAG+"#"+tag,message);
    }

}
