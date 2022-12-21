package com.jmmy.jmmyapp.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jmmy.jmmyapp.R;
import com.jmmy.jmmyapp.adaptercontent.RecyclerViewAdapter;
import com.jmmy.jmmyapp.floatwindow.FloatWindowManager;
import com.jmmy.jmmyapp.utils.LogUtils;
import com.jmmy.jmmyapp.utils.MyContacts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jmmy on 2017/12/13.
 */

public class ThirdActivity extends AppCompatActivity {
    private Context mContext = this;
    private String TAG = "ThirdActivity";
    private RecyclerView mRecycleView;
    private RecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        LogUtils.i(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        mRecycleView = findViewById(R.id.contacts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecycleView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerViewAdapter(mContext, false);
        List<MyContacts> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MyContacts myContacts = new MyContacts();
            myContacts.setName("张三" + i);
            myContacts.setPhone("8901249234" + i);
            list.add(myContacts);
        }
        mAdapter.setData(list);
        mRecycleView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        LogUtils.i(TAG,"onResume");
        super.onResume();
        FloatWindowManager.getInstance(this).showMaskView();
    }

    @Override
    protected void onDestroy() {
        LogUtils.i(TAG,"onDestory");
        super.onDestroy();
        FloatWindowManager.getInstance(this).dismissMaskView();
    }
}
