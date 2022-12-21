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
    private List<MyContacts> mContactsDataList = new ArrayList<>();
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
            return new SpecialViewHolder(view);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_recycleview_contact, parent, false);
            return new CommonViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommonViewHolder) {
            LogUtils.i(TAG, "CommonViewHolder onBindViewHolder = " + mContactsDataList.get(position).name);
            ((CommonViewHolder) holder).name.setText(mContactsDataList.get(position).name);
            ((CommonViewHolder) holder).number.setText(mContactsDataList.get(position).phone);
        } else if (holder instanceof SpecialViewHolder) {
            LogUtils.i(TAG, "SpecialViewHolder onBindViewHolder = " + mContactsDataList.get(position).name);
            ((SpecialViewHolder) holder).name.setText(mContactsDataList.get(position).name);
            ((SpecialViewHolder) holder).number.setText(mContactsDataList.get(position).phone);
        } else {
            LogUtils.i(TAG, "onBindViewHolder else branch.");
        }
    }

    @Override
    public int getItemCount() {
        return mContactsDataList == null ? 0 : mContactsDataList.size();
    }

    public void setData(List<MyContacts> list) {
        if (list != null && !list.isEmpty()) {
            mContactsDataList.clear();
            mContactsDataList.addAll(list);
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
            itemView.setOnClickListener(view -> LogUtils.i(TAG, "CommonViewHolder view"));
        }
    }

    static class SpecialViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView number;
        public SpecialViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_name);
            number = itemView.findViewById(R.id.text_number);
            itemView.setOnClickListener(view -> LogUtils.i(TAG, "SpecialViewHolder view"));
        }
    }
}
