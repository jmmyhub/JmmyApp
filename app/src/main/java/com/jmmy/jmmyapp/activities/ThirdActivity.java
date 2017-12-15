package com.jmmy.jmmyapp.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.GridView;

import com.jmmy.jmmyapp.adaptercontent.AdapterGridView;
import com.jmmy.jmmyapp.adaptercontent.AdapterListView;
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
    private GridView gridView ;
    private AdapterGridView adapterGridView;
    private int[] imgId = new int[]{R.mipmap.ic_launcher,R.mipmap.ic_launcher1,
            R.mipmap.ic_launcher2,R.mipmap.ic_launcher3,R.mipmap.ic_launcher4,R.mipmap.ic_launcher5,R.mipmap.ic_launcher,R.mipmap.ic_launcher1,
            R.mipmap.ic_launcher2,R.mipmap.ic_launcher3,R.mipmap.ic_launcher4,R.mipmap.ic_launcher5,R.mipmap.ic_launcher,R.mipmap.ic_launcher1,
            R.mipmap.ic_launcher2,R.mipmap.ic_launcher3,R.mipmap.ic_launcher4,R.mipmap.ic_launcher5,R.mipmap.ic_launcher,R.mipmap.ic_launcher1,
            R.mipmap.ic_launcher2,R.mipmap.ic_launcher3,R.mipmap.ic_launcher4,R.mipmap.ic_launcher5};
    private AdapterListView adapterListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        gridView = findViewById(R.id.gridview_third);
        List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
        for (int i = 0; i < imgId.length ; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("username","mayan"+i);
            map.put("imgId",imgId[i]);
            list.add(map);
        }
        adapterGridView = new AdapterGridView(mContext,list);
        gridView.setAdapter(adapterGridView);
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        return super.onGenericMotionEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int i = gridView.pointToPosition((int)event.getX(),(int)event.getY());
        Log.i("wjm","i: " + i);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
