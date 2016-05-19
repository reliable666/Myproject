package com.hx.mypresent.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.hx.mypresent.R;
import com.hx.mypresent.bean.SelectLvBean;
import com.hx.mypresent.bean.SelectBean;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/11.
 */
public class MySelectLvAdapter extends BaseAdapter {
    private SelectLvBean selectLvBean; //类
    List<SelectBean> selectBeans;//定义bean类;
    private Context context;//注入布局;

    public void setSelectLvBean(SelectLvBean selectLvBean) {
        this.selectLvBean = selectLvBean;
        Log.d("99", selectLvBean.getData().getItems().get(0).getCover_image_url());
        notifyDataSetChanged();
    }


    public MySelectLvAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return selectLvBean == null ? 0 : selectLvBean.getData().getItems().size();
    }

    @Override
    public Object getItem(int position) {
        return selectLvBean == null ? 0 :selectLvBean.getData().getItems().size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_select_lv, parent, false);

            holder = new ViewHolder(convertView);
            convertView.setTag(holder);//把已经初始化的viewHolder放到convertView中
        } else {
            //证明View不是新的,
            //把View里的holder取出来
            holder = (ViewHolder) convertView.getTag();
        }
        holder.headtv.setText(selectLvBean.getData().getItems().get(position).getTitle());
        //initImage(holder.headIv, selectLvBean.getData().getItems().get(position).getCover_image_url());
        Picasso.with(context).load(selectLvBean.getData().getItems().get(position).getCover_image_url()).placeholder(R.mipmap.lolo).error(R.mipmap.lolo).into(holder.headIv);
        Log.d("55", selectLvBean.getData().getItems().get(position).getCover_image_url());
//        SelectBean selectBean = selectBeans.get(position);
//        holder.headIv.setImageResource(selectBean.getHead());


        return convertView;
    }


    //内部类,文艺写法
    class ViewHolder {
        ImageView headIv;
        TextView headtv;
        public ViewHolder(View itemView) {
            headIv = (ImageView) itemView.findViewById(R.id.item_select_list_iv);
            headtv = (TextView) itemView.findViewById(R.id.item_select_list_tv);
        }
    }
    private void initImage(ImageView lvImage, String Url) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {

                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(lvImage,R.mipmap.lolo,R.mipmap.lolo);
        imageLoader.get(Url,listener);
    }
}
