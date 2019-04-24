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
        Log.i(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("userName");
        int age = bundle.getInt("age");
        this.setTitle(name+":"+age);
        TextView textView = findViewById(R.id.next_textView1);
        textView.setText(name+":"+age);
        button = findViewById(R.id.button2);
        JmmyUtils.getSocket();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i(TAG,"onStart");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NextActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });
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
