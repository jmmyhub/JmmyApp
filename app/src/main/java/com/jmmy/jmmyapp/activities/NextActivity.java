package com.jmmy.jmmyapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.jmmy.jmmyapp.R;

/**
 * Created by jmmy on 2017/12/10.
 */

public class NextActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("userName");
        int age = bundle.getInt("age");
        this.setTitle(name+":"+age);
        TextView textView = findViewById(R.id.next_textView1);
        textView.setText(name+":"+age);
    }
}
