package com.jmmy.jmmyapp.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.jmmy.jmmyapp.R;
import com.jmmy.jmmyapp.Utils.LogUtils;

/**
 * Created by jmmy on 2017/12/10.
 */

public class NextActivity extends AppCompatActivity{
    private String TAG = "NextActiviyty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        Log.i(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.i(TAG, "onStop");

    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.i(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.i(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.i(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.i(TAG, "onResume");
    }
}
