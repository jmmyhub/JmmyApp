package com.jmmy.jmmyapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jmmy.jmmyapp.R;
import com.jmmy.jmmyapp.utils.LogUtils;
import com.jmmy.jmmyapp.utils.MyContacts;
import com.jmmy.jmmyapp.adaptercontent.RecyclerViewAdapter;

import java.util.ArrayList;

public class FirstFragment extends BaseFragment {
    private static final String TAG = "FirstFragment";
    private Context mContext;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView mRecycleView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_layout,container,false);
        mRecycleView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecycleView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerViewAdapter(mContext, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = getArguments();
        LogUtils.i(TAG, "onResume bundle:" + (bundle != null ? bundle:  "null"));
        if (bundle != null) {
            ArrayList<MyContacts> list = bundle.getParcelableArrayList("contact");
            LogUtils.i(TAG, "onResume list:" + (list != null ? list.size() :  "null"));
            if (list != null && !list.isEmpty()) {
                mAdapter.setData(list);
                mRecycleView.setAdapter(mAdapter);
            }
        }
    }
}
