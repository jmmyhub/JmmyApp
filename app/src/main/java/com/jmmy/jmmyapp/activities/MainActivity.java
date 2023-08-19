package com.jmmy.jmmyapp.activities;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jmmy.jmmyapp.utils.ContactsUtils;
import com.jmmy.jmmyapp.R;
import com.jmmy.jmmyapp.utils.LogUtils;
import com.jmmy.jmmyapp.utils.MyContacts;
import com.jmmy.jmmyapp.broadcastreceviver.JmmyBroadcastReceiver;
import com.jmmy.jmmyapp.fragments.FirstFragment;
import com.jmmy.jmmyapp.fragments.FourthFragment;
import com.jmmy.jmmyapp.fragments.SecondFragment;
import com.jmmy.jmmyapp.fragments.ThirdFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private JmmyBroadcastReceiver mBroadcastReceiver;

    private final static String IntentAction = "com.jmmy.app.broadcastrecevices";

    ContentResolver mContentResolver;

    SettingObserver mSettingObserver = new SettingObserver(new Handler());

    private FragmentManager mSupportFragmentManager;

    private FirstFragment mFirstFragment;

    private SecondFragment mSecondFragment;

    private ThirdFragment mThirdFragment;

    private FourthFragment mFourthFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtils.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initBroadCastReciver();

        initContentResolver();

        initFragmentTransaction();

    }

    private void initView() {
        findViewById(R.id.button_first).setOnClickListener(this);
        findViewById(R.id.button_second).setOnClickListener(this);
        findViewById(R.id.button_third).setOnClickListener(this);
        findViewById(R.id.button_fourth).setOnClickListener(this);
    }

    private void initFragmentTransaction() {
        mSupportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mSupportFragmentManager.beginTransaction();
        //默认显示第一条
        if (mFirstFragment == null) {
            mFirstFragment = new FirstFragment();
            fragmentTransaction.add(R.id.fragment_content, mFirstFragment);
        } else {
            fragmentTransaction.show(mFirstFragment);
        }
        ArrayList<MyContacts> list = getContactsList();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("contact",list);
        mFirstFragment.setArguments(bundle);
        LogUtils.i(TAG, "button_first list size : " + (list != null ? list.size() : "null"));
        fragmentTransaction.commit();
    }

    private void initContentResolver() {
        mContentResolver = getApplicationContext().getContentResolver();
        mContentResolver.registerContentObserver(Settings.Global.getUriFor(Settings.Global.DEVELOPMENT_SETTINGS_ENABLED),
        false, mSettingObserver);
    }

    private void initBroadCastReciver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(IntentAction);
        mBroadcastReceiver = JmmyBroadcastReceiver.getInstance();
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction = mSupportFragmentManager.beginTransaction();
        //如果当前对象不等于null的话，那就隐藏他，不然会造成页面叠加现象
        if (mFirstFragment != null) {
            fragmentTransaction.hide(mFirstFragment);
        }
        if (mSecondFragment != null) {
            fragmentTransaction.hide(mSecondFragment);
        }
        if (mThirdFragment != null) {
            fragmentTransaction.hide(mThirdFragment);
        }
        if (mFourthFragment != null) {
            fragmentTransaction.hide(mFourthFragment);
        }
        switch (view.getId()) {
            case R.id.button_first:
                if (mFirstFragment == null) {
                    mFirstFragment = new FirstFragment();
                    fragmentTransaction.add(R.id.fragment_content, mFirstFragment);
                } else {
                    fragmentTransaction.show(mFirstFragment);
                }
                Toast.makeText(this, "first fragment", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_second:
                if (mSecondFragment == null) {
                    mSecondFragment = new SecondFragment();
                    fragmentTransaction.add(R.id.fragment_content, mSecondFragment);
                } else {
                    fragmentTransaction.show(mSecondFragment);
                }
                Toast.makeText(this, "second fragment", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_third:
                if (mThirdFragment == null) {
                    mThirdFragment = new ThirdFragment();
                    fragmentTransaction.add(R.id.fragment_content, mThirdFragment);
                } else {
                    fragmentTransaction.show(mThirdFragment);
                }
                Toast.makeText(this, "third fragment", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_fourth:
                if (mFourthFragment == null) {
                    mFourthFragment = new FourthFragment();
                    fragmentTransaction.add(R.id.fragment_content, mFourthFragment);
                } else {
                    fragmentTransaction.show(mFourthFragment);
                }
                Toast.makeText(this, "fourth fragment", Toast.LENGTH_SHORT).show();
                break;
        }
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume() {
        LogUtils.i(TAG, "MainActivity  onResume");
        super.onResume();
        DisplayManager displayManager = (DisplayManager) getSystemService(DISPLAY_SERVICE);
        Display[] displays = displayManager.getDisplays();
        for (Display display : displays) {
            LogUtils.i(TAG, "onResume display:" + display);
            if (display.getDisplayId() != Display.DEFAULT_DISPLAY) {
                Context context = createDisplayContext(display);
                Intent intent = new Intent();
                ActivityOptions activityOptions = ActivityOptions.makeBasic();
                activityOptions.setLaunchDisplayId(display.getDisplayId());
                intent.setClass(context, ThirdActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent, activityOptions.toBundle());
                WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.width = 144;
                layoutParams.gravity = Gravity.START;
                layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
                layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
                View view = View.inflate(context, R.layout.navi_status_bar, null);
                windowManager.addView(view, layoutParams);
            }
        }
    }

    private ArrayList<MyContacts> checkContactPermission(Activity activity, String[] permissions, int request) {
        //Android 6.0开始的动态权限，这里进行版本判断
        ArrayList<String> permissionsTemp = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(activity, permissions[i])
                    != PackageManager.PERMISSION_GRANTED) {
                permissionsTemp.add(permissions[i]);
            }
        }
        if (permissionsTemp.isEmpty()) {  //非初次进入App且已授权
            Toast.makeText(this, "已授权", Toast.LENGTH_SHORT).show();
            return ContactsUtils.getAllContacts(this);
        } else {
            Toast.makeText(this, "未授权", Toast.LENGTH_SHORT).show();
            //请求权限方法
            String[] permissionsNew = permissionsTemp.toArray(new String[0]);//将List转为数组
            requestPermissions(permissionsNew, request);
            //这个触发下面onRequestPermissionsResult这个回调
        }
        return ContactsUtils.getAllContacts(this);
    }

    private ArrayList<MyContacts> getContactsList() {
        String[] permissions = {Manifest.permission.READ_CONTACTS};
        ArrayList<MyContacts> contactsList = checkContactPermission(this, permissions, 200);
        return contactsList;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasAllGranted = true;
        //判断是否拒绝  拒绝后要怎么处理 以及取消再次提示的处理
        for (int grantResult : grantResults) {
            LogUtils.i(TAG, "grantResult = " + grantResult);
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                hasAllGranted = false;
                break;
            }
        }
        if (hasAllGranted) { //同意权限做的处理,开启服务提交通讯录
            Toast.makeText(this, "同意授权", Toast.LENGTH_SHORT).show();
        } else {    //拒绝授权做的处理，弹出弹框提示用户授权
            //dealwithPermiss(MainActivity.this, permissions[0]);
            Toast.makeText(this, "不同意授权", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBroadcastReceiver != null) {
            unregisterReceiver(mBroadcastReceiver);
        }
        mContentResolver.unregisterContentObserver(mSettingObserver);
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
                int value = Settings.Global.getInt(mContentResolver, Settings.Global.MODE_RINGER);
                LogUtils.i(TAG, "onChange:" + value);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }
            super.onChange(selfChange);
        }
    }
}
