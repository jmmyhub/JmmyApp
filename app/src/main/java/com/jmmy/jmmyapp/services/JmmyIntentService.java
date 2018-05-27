package com.jmmy.jmmyapp.services;

import android.app.IntentService;
import android.content.Intent;

public class JmmyIntentService extends IntentService {

    private boolean isRunning = false;
    private int count = 0;
    //private LocalBrocastManager localBrocastManager;
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public JmmyIntentService(String name) {
        super(name);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        //localBroadcastManager = LocalBroadcastManager.getInstance( this ) ;
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        sendThreadStatus("线程启动",count);
        try {
            Thread.sleep(1000l);
            sendServiceStatus("服务进行时。。。。");
            isRunning =true;
            count = 0;
            while (isRunning){
                count ++;
                if (count >= 100) {
                    isRunning =false;
                }
                Thread.sleep(50);
                sendThreadStatus("线程进行中。。。",count);
                sendThreadStatus("线程结束",count);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendServiceStatus(String s) {
        Intent intent = new Intent("");
    }

    private void sendThreadStatus(String 线程启动, int count) {
    }

}
