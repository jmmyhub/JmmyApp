package com.jmmy.jmmyapp;

import android.content.res.Configuration;
import android.util.Log;

import androidx.annotation.NonNull;

import com.jmmy.jmmyapp.ui.login.LoginActivity;
import com.jmmy.mvvmhabit.base.BaseApplication;
import com.jmmy.mvvmhabit.crash.CaocConfig;
import com.jmmy.mvvmhabit.utils.LogUtil;

public class JmmyApplication extends BaseApplication {
    private static final String TAG = "JmmyApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"JmmyApplication onCreate");
        LogUtil.init(true);
        //初始化全局异常崩溃
        initCrash();
        //内存泄漏检测
//        if (!LeakCanary.isInAnalyzerProcess(this)) {
//            LeakCanary.install(this);
//        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void initCrash() {
        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //背景模式,开启沉浸式
                .enabled(true) //是否启动全局异常捕获
                .showErrorDetails(true) //是否显示错误详细信息
                .showRestartButton(true) //是否显示重启按钮
                .trackActivities(true) //是否跟踪Activity
                .minTimeBetweenCrashesMs(2000) //崩溃的间隔时间(毫秒)
                .errorDrawable(R.mipmap.ic_launcher) //错误图标
                .restartActivity(LoginActivity.class) //重新启动后的activity
//                .errorActivity(YourCustomErrorActivity.class) //崩溃后的错误activity
//                .eventListener(new YourCustomEventListener()) //崩溃后的错误监听
                .apply();
    }
}
