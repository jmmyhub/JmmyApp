package com.jmmy.jmmyapp.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.jmmy.jmmyapp.R;

/**
 * Created by jmmy on 2017/12/13.
 */

public class ThirdActivity extends Activity {
    private Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
