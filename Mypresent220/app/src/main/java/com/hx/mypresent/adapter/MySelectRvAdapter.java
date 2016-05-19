package com.hx.mypresent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hx.mypresent.R;
import com.hx.mypresent.bean.SelectRvBean;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/12.
 */
public class MySelectRvAdapter extends RecyclerView.Adapter<MySelectRvAdapter.MyViewHolder> {
    private List<String> data;
    private Context context;
    private SelectRvBean selectRvBean;

    public void setSelectRvBean(SelectRvBean selectRvBean) {
        this.selectRvBean = selectRvBean;
        notifyDataSetChanged();
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public MySelectRvAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_select_rv, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(itemView);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.with(context).load(selectRvBean.getData().getSecondary_banners().get(position).getImage_url()).placeholder(R.mipmap.lolo).error(R.mipmap.lolo).into(holder.imageView);
//        Log.d("hahahei", " " + selectRvBean.getData().getSecondary_banners().get(position).getWebp_url());
// Picasso.with(context).load(selectLvBean.getData().getItems().get(position).getCover_image_url()).placeholder(R.mipmap.lolo).error(R.mipmap.lolo).into(holder.headIv);
    }

    @Override
    public int getItemCount() {
        return selectRvBean == null ? 0 : selectRvBean.getData().getSecondary_banners().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_select_recycle_iv);
        }
    }


}
