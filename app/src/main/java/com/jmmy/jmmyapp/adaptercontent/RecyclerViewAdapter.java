package com.jmmy.jmmyapp.adaptercontent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmmy.jmmyapp.R;
import com.jmmy.jmmyapp.utils.LogUtils;
import com.jmmy.jmmyapp.utils.MyContacts;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private static final String TAG = "RecyclerViewAdapter";
    private List<MyContacts> mList = new ArrayList<>();
    private Context mContext;
    private boolean isSpecialType;

    public RecyclerViewAdapter(Context context, boolean isSpecial){
        mContext = context;
        isSpecialType = isSpecial;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (isSpecialType) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_recycleview_contact, parent, false);
            return new CommonViewHolder(view);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_recycleview_contact, parent, false);
            return new SpecialViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommonViewHolder) {
            LogUtils.i(TAG, "onBindViewHolder = " + mList.get(position).name);
            ((CommonViewHolder) holder).name.setText(mList.get(position).name);
            ((CommonViewHolder) holder).number.setText(mList.get(position).phone);
        } else if (holder instanceof SpecialViewHolder) {
            ((SpecialViewHolder) holder).name.setText(mList.get(position).name);
            ((SpecialViewHolder) holder).number.setText(mList.get(position).phone);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setData(List<MyContacts> list) {
        if (mList.isEmpty()) {
            mList.addAll(list);
        } else {
            mList.clear();
            mList.addAll(list);
        }
        notifyDataSetChanged();
    }


    static class CommonViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView number;

        public CommonViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_name);
            number = itemView.findViewById(R.id.text_number);
        }
    }

    static class SpecialViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView number;

        public SpecialViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_name);
            number = itemView.findViewById(R.id.text_number);
        }
    }
}
