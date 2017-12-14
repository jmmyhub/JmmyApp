package com.jmmy.jmmyapp.AdapterContent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmmy.jmmyapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jmmy on 2017/12/14.
 */

public class AdapterGridView extends BaseAdapter {
    private Context mContext = null;
    private List<Map<String,Object>> mapList = new ArrayList<>();

    public AdapterGridView(Context mContext, List<Map<String, Object>> mapList) {
        this.mContext = mContext;
        this.mapList = mapList;
    }

    @Override
    public int getCount() {
        return mapList.size();
    }

    @Override
    public Object getItem(int i) {
        return mapList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    class ViewHolder {
        TextView textView1;
        ImageView imageView;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.item_gridview_third_userlist,viewGroup,false);
            viewHolder.textView1 = view.findViewById(R.id.textview_third);
            viewHolder.imageView = view.findViewById(R.id.imageview_third);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        String userName = mapList.get(i).get("username").toString();
        int picId = Integer.parseInt(mapList.get(i).get("imgId").toString());

        viewHolder.textView1.setText(userName);
        viewHolder.imageView.setImageResource(picId);

        return view;
    }
}
