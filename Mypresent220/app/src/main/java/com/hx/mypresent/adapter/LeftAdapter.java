package com.hx.mypresent.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hx.mypresent.R;
import com.hx.mypresent.bean.GiftBean;

/**
 * Created by dllo on 16/5/18.
 */

public class LeftAdapter extends BaseAdapter {
    int selectPos = 0;
    private Context context;
    private GiftBean response;

    public LeftAdapter(Context context, GiftBean response) {
        this.context = context;
        this.response = response;
    }

    @Override
    public int getCount() {
        return response == null ? 0 : response.getData().getCategories().size();
    }

    public void setSelectPos(int pos){
        this.selectPos = pos;
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_sort_left_gift_lv, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.textView.setText(response.getData().getCategories().get(position).getName());
        return convertView;
    }

    class ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            textView = (TextView) itemView.findViewById(R.id.item_sort_left_tv);
        }
    }


}

