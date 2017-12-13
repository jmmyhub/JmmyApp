package com.jmmy.jmmyapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jmmy.jmmyapp.R;

/**
 * Created by jmmy on 2017/12/10.
 */

public class NextActivity extends Activity {
    private Button button;
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
        button = findViewById(R.id.button2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("wjm","onStart");
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
        Log.i("wjm","onStop");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("wjm","onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("wjm","onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("wjm","onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("wjm","onResume");
    }
}
