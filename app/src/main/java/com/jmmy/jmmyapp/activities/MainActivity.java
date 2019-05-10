package com.jmmy.jmmyapp.activities;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Button;

import com.jmmy.jmmyapp.Utils.LogUtils;
import com.jmmy.jmmyapp.R;
import com.jmmy.jmmyapp.broadcastreceviver.JmmyBroadcastReceiver;

public class MainActivity extends Activity {
    private static String TAG = "MainActivity";
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private JmmyBroadcastReceiver broadcastReceiver;
    private final static String IntentAction = "com.jmmy.app.broadcastrecevices";

    ContentResolver contentResolver;
    SettingObserver settingObserver = new SettingObserver(new Handler());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtils.i(TAG, "MainActivity  onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(IntentAction);
        broadcastReceiver = JmmyBroadcastReceiver.getInstance();
        registerReceiver(broadcastReceiver, intentFilter);

        contentResolver = getApplicationContext().getContentResolver();
        contentResolver.registerContentObserver(Settings.Global.getUriFor(Settings.Global.DEVELOPMENT_SETTINGS_ENABLED),
                false, settingObserver);

    }

    @Override
    protected void onStart() {
        LogUtils.i(TAG, "MainActivity  onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        LogUtils.i(TAG, "MainActivity  onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        LogUtils.i(TAG, "MainActivity  onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
        contentResolver.unregisterContentObserver(settingObserver);
    }

    class SettingObserver extends ContentObserver {

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public SettingObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            try {
                Settings.Global.getInt(contentResolver, Settings.Global.MODE_RINGER);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }
            super.onChange(selfChange);
        }
    }

    private void initView() {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
    }
}
