package com.hx.mypresent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hx.mypresent.R;
import com.hx.mypresent.bean.CBean;

import it.sephiroth.android.library.picasso.Picasso;

import static com.hx.mypresent.R.id.item_frm_commments_content;

/**
 * Created by dllo on 16/5/14.
 */
public class MyCommentsAdapter extends RecyclerView.Adapter<MyCommentsAdapter.MyViewHolder> {
    private Context context;
    private CBean cBean;

    public void setcBean(CBean cBean) {
        this.cBean = cBean;
        notifyDataSetChanged();
    }

    public MyCommentsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_frm_comments, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.nikeNameTv.setText(cBean.getData().getComments().get(position).getUser().getNickname());
        holder.timeTv.setText(cBean.getData().getComments().get(position).getCreated_at());


        holder.contentTv.setText(cBean.getData().getComments().get(position).getContent());
        Picasso.with(context).load(cBean.getData().getComments().get(position).getUser().getAvatar_url()).placeholder(R.mipmap.lolo).error(R.mipmap.lolo).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return cBean == null ? 0 : cBean.getData().getComments().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nikeNameTv, timeTv, contentTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_frm_commments_iv);
            nikeNameTv = (TextView) itemView.findViewById(R.id.item_frm_commments_nickname);
            timeTv = (TextView) itemView.findViewById(R.id.item_frm_commments_time);
            contentTv = (TextView) itemView.findViewById(item_frm_commments_content);
        }
    }
}
