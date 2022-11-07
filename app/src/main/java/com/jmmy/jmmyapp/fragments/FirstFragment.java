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
import com.jmmy.jmmyapp.utils.MyContacts;
import com.jmmy.jmmyapp.activities.MainActivity;
import com.jmmy.jmmyapp.adaptercontent.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends BaseFragment implements
        MainActivity.OnFragmentInteractionListener {
    private Context mContext;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView mRecView;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment_layout,container,false);
        mRecView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerViewAdapter(mContext, false);
        List<MyContacts> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MyContacts myContacts = new MyContacts();
            myContacts.setName("张三" + i);
            myContacts.setPhone("8901249234" + i);
            list.add(myContacts);
        }
        mAdapter.setData(list);
        mRecView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onFragmentInteraction(ArrayList<MyContacts> list) {
        mAdapter.setData(list);
    }
}
