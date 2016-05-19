package com.hx.mypresent.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.hx.mypresent.bean.SelectCarouselBean;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/10.
 */
public class MySelectPagerAdapter extends PagerAdapter {
    private ArrayList<ImageView> list;
    private SelectCarouselBean selectCarouselBean;
    private Context context;


    public MySelectPagerAdapter(Context context) {
        this.context = context;
    }

    public void setSelectCarouselBean(SelectCarouselBean selectCarouselBean) {
        this.selectCarouselBean = selectCarouselBean;
        notifyDataSetChanged();
    }

    public MySelectPagerAdapter(ArrayList<ImageView> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        initImage();

        ImageView view = list.get(position);
        ViewParent parent = view.getParent();
        if (parent != null) {
            ViewGroup group = (ViewGroup) parent;
            group.removeView(view);
        }
        ((ViewPager) container).addView(list.get(position));
        return list.get(position);
    }

//    private void initImage() {
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
//            @Override
//            public Bitmap getBitmap(String url) {
//
//                return null;
//            }
//
//            @Override
//            public void putBitmap(String url, Bitmap bitmap) {
//
//            }
//        });
//        ImageLoader.ImageListener listener = ImageLoader.getImageListener()
//    }


}
