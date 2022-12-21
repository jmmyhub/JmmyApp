package com.jmmy.jmmyapp.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jmmy.jmmyapp.R;

public class FourthFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "";
    private Context mContext;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_fourth_layout,container,false);
        view.findViewById(R.id.button_fourth_activity).setOnClickListener(this::onClick);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_fourth_activity:
                if (mContext != null) {
                    Intent intent = new Intent();
                    ComponentName componentName = new ComponentName(mContext.getPackageName(),
                        "com.jmmy.jmmyapp.activities.FourThActivity");
                    intent.setComponent(componentName);
                    mContext.startActivity(intent);
                }
                break;
            default:
                break;
        }
    }
}
