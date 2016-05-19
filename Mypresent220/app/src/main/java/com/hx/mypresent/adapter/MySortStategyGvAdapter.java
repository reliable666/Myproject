package com.hx.mypresent.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hx.mypresent.R;
import com.hx.mypresent.bean.SortStatrgyBean;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/18.
 */
public class MySortStategyGvAdapter extends BaseAdapter {
    private SortStatrgyBean sortStatrgyBeans;
    private Context context;

    private int listPos;

    public void setListPos(int listPos) {
        this.listPos = listPos;
    }

    public MySortStategyGvAdapter(Context context) {
        this.context = context;
    }

    public void setSortStatrgyBeans(SortStatrgyBean sortStatrgyBeans) {
        this.sortStatrgyBeans = sortStatrgyBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return sortStatrgyBeans == null ? 0 : sortStatrgyBeans.getData().getChannel_groups().get(listPos).getChannels().size();
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
        MyViewHolder myViewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_stategy_gv, parent, false);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        myViewHolder.myRvTv.setText(sortStatrgyBeans.getData().getChannel_groups().get(listPos).getChannels().get(position).getName());
        Log.d("MySortStategyGvAdapter", "position:" + position);

        Log.d("MySortStategyGvAdapter", sortStatrgyBeans.getData().getChannel_groups().get(listPos).getChannels().get(position).getName());
        Picasso.with(context).load(sortStatrgyBeans.getData().getChannel_groups().get(listPos).getChannels().
                get(position).getIcon_url()).placeholder(R.mipmap.lolo).error(R.mipmap.lolo).into(myViewHolder.myRvIv);
        return convertView;
    }

    class MyViewHolder {
        ImageView myRvIv;
        TextView myRvTv;

        public MyViewHolder(View itemView) {
            myRvIv = (ImageView) itemView.findViewById(R.id.item_strategy_gv_iv);
            myRvTv = (TextView) itemView.findViewById(R.id.item_strategy_gv_tv);
        }
    }
}
