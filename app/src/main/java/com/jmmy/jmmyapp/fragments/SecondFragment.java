package com.jmmy.jmmyapp.fragments;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.jmmy.jmmyapp.R;
import com.jmmy.jmmyapp.Utils.LogUtils;
import com.jmmy.jmmyapp.services.JmmyServiceConnection;

public class SecondFragment extends BaseFragment {
    private static final String TAG = "SecondFragment";
    private Context mContext;
    private JmmyServiceConnection mServiceConnection;
    private NotificationManager manager;
    @Override
    public void onAttach(@NonNull Context context) {
        mContext = context;
        mServiceConnection = JmmyServiceConnection.getInstance();
        manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(new NotificationChannel("jmmy_notification",
                "jmmy_notification",NotificationManager.IMPORTANCE_HIGH));
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment_layout,container,false);
        Button button5 = view.findViewById(R.id.button5);
        button5.setOnClickListener(button_view -> {
            LogUtils.i(TAG, "onCreateView button5 onClick ");
            Intent intent = new Intent();
            intent.setClassName(mContext.getPackageName(), "com.jmmy.jmmyapp.services.JmmyServices");
            mContext.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
            Notification.Builder builder = new Notification.Builder(mContext, "jmmy_notification");
            Intent intent1 = new Intent();
            intent1.setClassName(mContext.getPackageName(), "com.jmmy.jmmyapp.activities.NextActivity");
            PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent1, PendingIntent.FLAG_IMMUTABLE);
            Notification.Action.Builder builder1 = new Notification.Action.Builder(
                    Icon.createWithResource(mContext, R.mipmap.ic_launcher), "disconnect", pendingIntent);
            builder.setContentTitle("Jmmy_app");
            builder.setContentText("通知运行");
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher));
            builder.setActions(builder1.build());
            builder.setSmallIcon(R.mipmap.ic_launcher);
            manager.notify(0, builder.build());
        });

        Button button6 = view.findViewById(R.id.button6);
        button6.setOnClickListener(button_view -> {
            LogUtils.i(TAG, "onCreateView button6 onClick mServiceConnection:" + mServiceConnection);
            try {
                if (mServiceConnection != null) {
                    mServiceConnection.getAidlInterface().setCount(10);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        Button button7 = view.findViewById(R.id.button7);
        button7.setOnClickListener(button_view -> {
            LogUtils.i(TAG, "onCreateView button7 onClick mServiceConnection:" + mServiceConnection);
            if (mServiceConnection != null) {
                mContext.unbindService(mServiceConnection);
            }
        });

        return view;
    }

}
