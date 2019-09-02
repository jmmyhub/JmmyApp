package com.jmmy.jmmyapp.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.GridView;

import com.jmmy.jmmyapp.Utils.LogUtils;
import com.jmmy.jmmyapp.adaptercontent.GridViewAdapter;
import com.jmmy.jmmyapp.adaptercontent.ListViewAdapter;
import com.jmmy.jmmyapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jmmy on 2017/12/13.
 */

public class ThirdActivity extends Activity {
    private Context mContext = this;
    private String TAG = "ThirdActivity";
    private GridView gridView ;
    private GridViewAdapter gridViewAdapter;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        LogUtils.i(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        gridView = findViewById(R.id.gridview_third);
        List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 14 ; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("username","mayan"+i);
            map.put("imgId",R.mipmap.ic_launcher);
            list.add(map);
        }
        gridViewAdapter = new GridViewAdapter(mContext,list);
        gridView.setAdapter(gridViewAdapter);
    }

    @Override
    protected void onStart() {
        LogUtils.i(TAG,"onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        LogUtils.i(TAG,"onResume");
        super.onResume();
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        return super.onGenericMotionEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int i = gridView.pointToPosition((int)event.getX(),(int)event.getY());
        LogUtils.i(TAG,"i: " + i);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onPause() {
        LogUtils.i(TAG,"onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        LogUtils.i(TAG,"onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        LogUtils.i(TAG,"onDestory");
        super.onDestroy();
    }
}
