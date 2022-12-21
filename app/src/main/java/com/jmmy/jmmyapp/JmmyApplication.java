package com.jmmy.jmmyapp;

import android.app.Application;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

public class JmmyApplication extends Application {
    private static final String TAG = "JmmyApplication";
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
