package com.jmmy.jmmyapp.activities;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.jmmy.jmmyapp.Utils.LogUtils;
import com.jmmy.jmmyapp.adaptercontent.ListViewAdapter;
import com.jmmy.jmmyapp.R;
import com.jmmy.jmmyapp.broadcastreceviver.JmmyBroadcastReceiver;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {
    private static String TAG = "MainActivity";
    private Button button;
    private Context context = this;
    private ListView listView ;
    private ListViewAdapter listViewAdapter = null;
    private JmmyBroadcastReceiver broadcastReceiver ;
    private final static String IntentAction = "com.jmmy.app.broadcastrecevices";

    ContentResolver contentResolver;
    SettingObserver settingObserver =new SettingObserver(new Handler());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtils.i(TAG,"MainActivity  onCreate" );
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
        //getSumId();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(IntentAction);
        broadcastReceiver = JmmyBroadcastReceiver.getInstance();
        registerReceiver(broadcastReceiver ,intentFilter);
        sendBroadcast(new Intent(IntentAction));

        contentResolver = getApplicationContext().getContentResolver();
        contentResolver.registerContentObserver(Settings.Global.getUriFor(Settings.Global.DEVELOPMENT_SETTINGS_ENABLED),
                false,settingObserver);
    }
    class SettingObserver extends ContentObserver{

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public SettingObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            Log.i("jmmy",".........................");
            try {
                Settings.Global.getInt(contentResolver,Settings.Global.MODE_RINGER);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }
            super.onChange(selfChange);
        }
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

    @Override
    protected void onStart() {
        LogUtils.i(TAG,"MainActivity  onStart" );
        super.onStart();
    }

    @Override
    protected void onResume()
    {
        LogUtils.i(TAG,"MainActivity  onResume" );
        new Thread(runnable).start();
        super.onResume();
    }

    private String getSumId (){
        String readLine = " ";
        String html = "";
        try{
            URL newUrl = new URL("https://www.baidu.com");
            URLConnection connection = newUrl.openConnection();
            DataInputStream dataInputStream = new DataInputStream(connection.getInputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(dataInputStream,"UTF-8"));
            while ((readLine = in.readLine()) != null){
                html = html + readLine;
                LogUtils.i(TAG,"MainActivity : " + readLine);
            }
            in.close();
        }catch (Exception e){
            LogUtils.i(TAG,"MainActivity : " + e.toString());
        }
        return html;
    }

    @Override
    protected void onPause() {
        LogUtils.i(TAG,"MainActivity  onPause" );
        super.onPause();
    }

    @Override
    protected void onStop() {
        LogUtils.i(TAG,"MainActivity  onStop" );
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        LogUtils.i(TAG,"MainActivity  onDestory" );
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
            contentResolver.unregisterContentObserver(settingObserver);
        }
        super.onDestroy();
    }
    private final static int MSG1 = 0;
    static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String a = bundle.getString("jmmy","you");
            LogUtils.i(TAG,"handle message " + a);
        }
    };

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putString("jmmy","nihao"+getSumId());
            message.setData(bundle);
            handler.sendMessage(message);
        }
    };
}
