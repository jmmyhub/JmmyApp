package com.jmmy.jmmyapp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.jmmy.jmmyapp.adaptercontent.ListViewAdapter;
import com.jmmy.jmmyapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {
    private Button button;
    private Context context = this;
    private ListView listView ;
    private ListViewAdapter listViewAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 14 ; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("username","mayan"+i);
            map.put("email","146398439140"+i);
            map.put("imgId",R.mipmap.ic_launcher);
            list.add(map);
        }
        listViewAdapter = new ListViewAdapter(context,list);
        initView();
        listView.setAdapter(listViewAdapter);
    }

    private void initView() {
        button = findViewById(R.id.button);
        listView = findViewById(R.id.listView_main);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.button:
                        Log.i("wjm","nihao button");
                        Toast.makeText(context,"show list.....",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this,NextActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("userName","mayan");
                        bundle.putInt("age",26);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                }
            }
        });

    }
}
