package com.jmmy.jmmyapp.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jmmy.jmmyapp.R;
import com.jmmy.jmmyapp.Utils.JmmyUtils;
import com.jmmy.jmmyapp.Utils.LogUtils;

/**
 * Created by jmmy on 2017/12/10.
 */

public class NextActivity extends Activity {
    private String TAG = "NextActiviyty";
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        Log.i(TAG,"onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i(TAG,"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.i(TAG,"onStop");

    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.i(TAG,"onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.i(TAG,"onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.i(TAG,"onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.i(TAG,"onResume");
    }
}
