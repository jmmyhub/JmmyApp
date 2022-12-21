package com.jmmy.jmmyapp.activities;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jmmy.jmmyapp.utils.LogUtils;

public class PermissionActivity extends Activity {
    private static final String TAG = "PermisionActivity";

    private static String[] mPermissionArrarys = new String[] {
            Manifest.permission.INTERNET,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissions(mPermissionArrarys, 200);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200) {
            LogUtils.i(TAG, "onRequestPermissionsResult");
            int length = grantResults.length;
            for (int i = 0; i < length; i++) {
                boolean isShow = shouldShowRequestPermissionRationale(permissions[i]);

                LogUtils.i(TAG, "permission:" + permissions[i] + ",grantResult:" + grantResults[i] + ",isShow:" + isShow);
            }
        }
    }
}
