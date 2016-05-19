package com.hx.mypresent.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hx.mypresent.BuildConfig;
import com.hx.mypresent.R;
import com.hx.mypresent.bean.SortStatrgyBean;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/17.
 */
public class MySortStategyLvAdapter extends BaseAdapter {
    private Context context;

    private SortStatrgyBean sortStatrgyBean;

    private MySortStategyGvAdapter mySortStategyGvAdapter;

    public void setSortStatrgyBean(SortStatrgyBean sortStatrgyBean) {
        this.sortStatrgyBean = sortStatrgyBean;
        notifyDataSetChanged();
    }

    public MySortStategyLvAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        //Log.d("获取数量", "sortStatrgyBean:" + sortStatrgyBean.getData());
        return sortStatrgyBean == null ? 0 : sortStatrgyBean.getData().getChannel_groups().size();

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
        viewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_strategy_lv, parent, false);
            holder = new viewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.nameTv.setText(sortStatrgyBean.getData().getChannel_groups().get(position).getName());

        //直接new出一个 gridView的内部类来实现拉取

        mySortStategyGvAdapter = new MySortStategyGvAdapter(context);
        mySortStategyGvAdapter.setSortStatrgyBeans(sortStatrgyBean);
        mySortStategyGvAdapter.setListPos(position);
        holder.gridView.setAdapter(mySortStategyGvAdapter);

//        {

//            private SortStatrgyBean sortStatrgyBeans;
//
//            public void setSortStatrgyBeans(SortStatrgyBean sortStatrgyBeans) {
//                this.sortStatrgyBeans = sortStatrgyBeans;
//                notifyDataSetChanged();
//            }
//
//            @Override
//            public int getCount() {
//                return sortStatrgyBean == null ? 0 : sortStatrgyBean.getData().getChannel_groups().size();
//            }
//
//            @Override
//            public Object getItem(int position) {
//                return null;
//            }
//
//            @Override
//            public long getItemId(int position) {
//                return 0;
//            }
//
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                MyViewHolder myViewHolder = null;
//
//                if (convertView == null) {
//                    convertView = LayoutInflater.from(context).inflate(R.layout.item_stategy_gv, parent, false);
//                    myViewHolder = new MyViewHolder(convertView);
//                    convertView.setTag(myViewHolder);
//                } else {
//                    myViewHolder = (MyViewHolder) convertView.getTag();
//                }
//                myViewHolder.myRvTv.setText(sortStatrgyBean.getData().getChannel_groups().get(position).getChannels().get(position).getName());
//                Picasso.with(context).load(sortStatrgyBean.getData().getChannel_groups().get(position).getChannels().
//                        get(position).getIcon_url()).placeholder(R.mipmap.lolo).error(R.mipmap.lolo).into(myViewHolder.myRvIv);
//                return convertView;
//            }
//
//            class MyViewHolder {
//                ImageView myRvIv;
//                TextView myRvTv;
//
//                public MyViewHolder(View itemView) {
//                    myRvIv = (ImageView) itemView.findViewById(R.id.item_strategy_gv_iv);
//                    myRvTv = (TextView) itemView.findViewById(R.id.item_strategy_gv_tv);
//                }
//            }
//        });
        return convertView;
    }

    class viewHolder {

        TextView nameTv;
        GridView gridView;

        public viewHolder(View itemView) {
            nameTv = (TextView) itemView.findViewById(R.id.item_strategy_tv);
            gridView = (GridView) itemView.findViewById(R.id.item_strategy_gv);
        }
    }
}
