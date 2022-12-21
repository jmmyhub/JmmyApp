package com.jmmy.jmmyapp.floatwindow;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.jmmy.jmmyapp.R;
import com.jmmy.jmmyapp.utils.LogUtils;

public class FloatWindowManager {
    private static final String TAG = "FloatWindowManager ";
    private volatile static FloatWindowManager floatWindowManager;
    private Context mContext;
    private View mView;
    private WindowManager.LayoutParams params;
    private WindowManager windowManager;
    private boolean isShow = false;

    private FloatWindowManager (Context context) {
        init(context);
    }

    public static synchronized FloatWindowManager getInstance(Context context){
        if (floatWindowManager == null) {
            floatWindowManager = new FloatWindowManager(context);
        }
        return floatWindowManager;
    }

    private void init(Context context){
        mContext = context;
        windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mView = LayoutInflater.from(context).inflate(R.layout.float_window_root_view, null, false);
        params = getFloatWindowParams();
    }

    private WindowManager.LayoutParams getFloatWindowParams() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        DisplayManager displayManager = (DisplayManager) mContext.getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = displayManager.getDisplays();
        Display display = null;
        for (Display value : displays) {
            LogUtils.i(TAG, "getFloatWindowParmas:" + value);
            if (value.getDisplayId() != Display.DEFAULT_DISPLAY && display.isValid()) {
                display = value;
                break;
            }
        }
        if (display != null) {
            display.getMetrics(displayMetrics);
            params.width = displayMetrics.widthPixels;
            params.height = 360;
            params.alpha = 0.2f;
            params.gravity = Gravity.START| Gravity.BOTTOM;
            params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        }
        return params;
    }

    public void showMaskView() {
        if (!isShow && windowManager != null) {
            windowManager.addView(mView, params);
            isShow = true;
        }
    }

    public void dismissMaskView(){
        if (isShow && windowManager != null) {
            windowManager.removeViewImmediate(mView);
            isShow = false;
        }
    }
}
