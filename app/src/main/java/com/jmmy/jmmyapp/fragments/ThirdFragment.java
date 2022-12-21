package com.jmmy.jmmyapp.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.jmmy.jmmyapp.R;

public class ThirdFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "ThirdFragment";
    private Context mContext;
    private Button mButton;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_third_layout,container,false);
        mButton = view.findViewById(R.id.button_third_fragment);
        mButton.setOnClickListener(this::onClick);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_third_fragment:
                if (mContext != null) {
                    Intent intent = new Intent();
                    ComponentName componentName = new ComponentName(mContext.getPackageName(),
                        "com.jmmy.jmmyapp.activities.NextActivity");
                    intent.setComponent(componentName);
                    mContext.startActivity(intent);
                }
                break;
            default:
                break;
        }
    }
}
