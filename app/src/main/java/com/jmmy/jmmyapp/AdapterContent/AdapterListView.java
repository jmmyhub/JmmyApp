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
 * Created by jmmy on 2017/12/10.
 */

public class AdapterListView extends BaseAdapter {
    private Context mContext = null;
    private List<Map<String,Object>> mapList = new ArrayList<>();

    public AdapterListView(Context context, List<Map<String,Object>> list) {
        this.mContext = context;
        this.mapList = list;
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
        TextView textView2;
        ImageView imageView;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.item_listview_main_userlist,viewGroup,false);
            viewHolder.textView1 = view.findViewById(R.id.item_textView1);
            viewHolder.textView2 = view.findViewById(R.id.item_textView2);
            viewHolder.imageView = view.findViewById(R.id.item_imageView);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        String userName = mapList.get(i).get("username").toString();
        String email = mapList.get(i).get("email").toString();
        int picId = Integer.parseInt(mapList.get(i).get("imgId").toString());

        viewHolder.textView1.setText(userName);
        viewHolder.textView2.setText(email);
        viewHolder.imageView.setImageResource(picId);

        return view;
    }
}
