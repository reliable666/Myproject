package com.hx.mypresent.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.hx.mypresent.BuildConfig;
import com.hx.mypresent.R;
import com.hx.mypresent.bean.BBean;
import com.hx.mypresent.bean.HotRvBean;
import com.hx.mypresent.myinterface.MyRvOnclickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/9.
 */
public class MyHotRvAdapter extends RecyclerView.Adapter<MyHotRvAdapter.ViewHolder> {
    private List<String> data;
    private Context context;
    private HotRvBean hotRvBean;
    private MyRvOnclickListener myRvOnclickListener;
    private BBean bBean;

    public void setMyRvOnclickListener(MyRvOnclickListener myRvOnclickListener) {
        this.myRvOnclickListener = myRvOnclickListener;
    }

    public void setHotRvBean(HotRvBean hotRvBean) {
        this.hotRvBean = hotRvBean;
        notifyDataSetChanged();
    }

    public void setData(List<String> data) {
        this.data = data;
//        heights = new ArrayList<>();
//        Random random = new Random();
//        for (int i = 0; i <data.size() ; i++) {
//            heights.add(random.nextInt(400)+150);
//        }
        notifyDataSetChanged();
    }

    public MyHotRvAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_hot_rv, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ViewGroup.LayoutParams parms = holder.textView.getLayoutParams();
        Random random = new Random();
        int randomHeight = random.nextInt(100) + 150;
        //将高度设置成随机数
        parms.height = randomHeight;
        holder.textView.setLayoutParams(parms);
        holder.textView.setText(hotRvBean.getData().getItems().get(position).getData().getName());
        holder.textViewMoney.setText(hotRvBean.getData().getItems().get(position).getData().getPrice());
//        initImage(holder.imageView, hotRvBean.getData().getItems().get(position).getData().getCover_image_url());
        Picasso.with(context).load(hotRvBean.getData().getItems().get(position).getData().getCover_image_url()).placeholder(R.mipmap.lolo).error(R.mipmap.lolo).into(holder.imageView);

        if (myRvOnclickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = hotRvBean.getData().getItems().get(position).getData().getId();
//                    String name = bBean.getData().getName();
//                    String money =bBean.getData().getPrice();
//                    String content = bBean.getData().getDescription();
                    myRvOnclickListener.myOnclick(id);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return hotRvBean == null ? 0 : hotRvBean.getData().getItems().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView, textViewMoney;


        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.frm_hot_item_tv);
            textViewMoney = (TextView) itemView.findViewById(R.id.frm_hot_item_tv_money);
            imageView = (ImageView) itemView.findViewById(R.id.frm_hot_item_iv);
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
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(lvImage, R.mipmap.ic_launchers, R.mipmap.ic_launchers);
        imageLoader.get(Url, listener);
    }


}
