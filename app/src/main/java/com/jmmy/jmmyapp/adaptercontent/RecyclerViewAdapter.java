package com.jmmy.jmmyapp.adaptercontent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmmy.jmmyapp.R;
import com.jmmy.jmmyapp.Utils.LogUtils;
import com.jmmy.jmmyapp.Utils.MyContacts;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private static final String TAG = "RecyclerViewAdapter";
    private List<MyContacts> mList;
    private Context mContext;

    public RecyclerViewAdapter(Context context){
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycleview_contact,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            LogUtils.i(TAG, "onBindViewHolder = " + mList.get(position).name);
            ((ViewHolder) holder).name.setText(mList.get(position).name);
            ((ViewHolder) holder).number.setText(mList.get(position).phone);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setData(List<MyContacts> list){
        mList = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView number;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_name);
            number = itemView.findViewById(R.id.text_number);
        }
    }
}
