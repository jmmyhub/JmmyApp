package com.jmmy.jmmyapp.fragments;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.jmmy.jmmyapp.R;
import com.jmmy.jmmyapp.services.JmmyServiceConnection;
import com.jmmy.jmmyapp.utils.LogUtils;

public class SecondFragment extends BaseFragment implements View.OnClickListener{
    private static final String TAG = "SecondFragment";
    private Context mContext;
    private JmmyServiceConnection mServiceConnection;
    private NotificationManager mNotificationManager;
    private TextView mCountValue;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
        mServiceConnection = JmmyServiceConnection.getInstance();
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.createNotificationChannel(new NotificationChannel("jmmy_notification",
            "jmmy_notification",NotificationManager.IMPORTANCE_HIGH));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_second_layout,container,false);
        Button bindService = view.findViewById(R.id.button_bind_service);
        Button transferData = view.findViewById(R.id.button_transfer_data);
        Button unbindService = view.findViewById(R.id.button_unbind_service);
        bindService.setOnClickListener(this::onClick);
        transferData.setOnClickListener(this::onClick);
        unbindService.setOnClickListener(this::onClick);
        mCountValue = view.findViewById(R.id.count_value);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_bind_service:
                LogUtils.i(TAG, "onCreateView bindService onClick ");

                Intent intent = new Intent();
                intent.setClassName("com.jmmy.jmmy2", "com.jmmy.jmmy2.service.Jmmy2Service");
                mContext.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);

                Notification.Builder builder = new Notification.Builder(mContext, "jmmy_notification");
                Intent startNextActivity = new Intent();
                startNextActivity.setClassName(mContext.getPackageName(), "com.jmmy.jmmyapp.activities.NextActivity");
                PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, startNextActivity, PendingIntent.FLAG_IMMUTABLE);
                Notification.Action.Builder builder1 = new Notification.Action.Builder(
                    Icon.createWithResource(mContext, R.mipmap.ic_launcher), "disconnect", pendingIntent);
                builder.setContentTitle("Jmmy_app");
                builder.setContentText("通知运行");
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher));
                builder.setActions(builder1.build());
                builder.setSmallIcon(R.mipmap.ic_launcher);
                mNotificationManager.notify(0, builder.build());
                break;
            case R.id.button_transfer_data:
                LogUtils.i(TAG, "onCreateView button_transfer_data onClick mServiceConnection:" + mServiceConnection);
                try {
                    if (mServiceConnection != null && mServiceConnection.getJmmyAidlInterface() != null) {
                        int value = mServiceConnection.getJmmyAidlInterface().getCount();
                        LogUtils.i(TAG, "onCreateView button_transfer_data onClick value:" + value);
                        if (mCountValue != null) {
                            mCountValue.setText(value + "");
                        }
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button_unbind_service:
                LogUtils.i(TAG, "onCreateView unbindService onClick mServiceConnection:" + mServiceConnection);
                if (mServiceConnection != null) {
                    mContext.unbindService(mServiceConnection);
                }
                break;
            default:
                break;
        }
    }
}
