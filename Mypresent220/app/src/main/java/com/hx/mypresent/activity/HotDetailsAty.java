package com.hx.mypresent.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hx.mypresent.BuildConfig;
import com.hx.mypresent.R;
import com.hx.mypresent.adapter.MyHotDetailsPagerAdapter;
import com.hx.mypresent.adapter.MyHotDetailAdapter;
import com.hx.mypresent.bean.BBean;
import com.hx.mypresent.bean.SelectLvBean;
import com.hx.mypresent.fragment.CommentsFragment;
import com.hx.mypresent.fragment.MyFragment;
import com.hx.mypresent.fragment.PhotoShowFrm;
import com.hx.mypresent.fragment.SelectFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by dllo on 16/5/13.
 */
public class HotDetailsAty extends BaseActivity {
    private Toolbar toolbar;
    private MyHotDetailAdapter detailAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager, viewPager1;
    private List<Fragment> fragments;
    private BBean bBean;
    private TextView nameTv, moneyTv, contentTv;

    //轮播图**
    private MyHotDetailsPagerAdapter myHotDetailsPagerAdapter;
    private ViewPager myviewPager;
    //用于小圆点图片
    private List<ImageView> dotViewList;
    //用于存轮播效果图片
    private List<ImageView> list;
    LinearLayout dotLayout;
    private LayoutInflater inflater;
    //当前页面
    private int currentItem = 0;
    //是否自动轮播
    boolean isAutoplay = true;
    private ScheduledExecutorService scheduledExecutorService;
    private ImageView imgOne, imgTwo, imgThree;


    //轮播图**开Handler

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if (msg.what == 100) {
                myviewPager.setCurrentItem(currentItem);
            }
        }
    };

    @Override
    protected int getLayout() {
        return R.layout.aty_hot_details;
    }


    @Override
    protected void initView() {
        inflater = LayoutInflater.from(HotDetailsAty.this);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        tabLayout = (TabLayout) findViewById(R.id.details_aty_tab);
        viewPager = (ViewPager) findViewById(R.id.details_aty_viewpager);
        myviewPager = (ViewPager) findViewById(R.id.details_aty_vp);
        dotLayout = (LinearLayout) findViewById(R.id.details_aty_dotLayout);
        nameTv = (TextView) findViewById(R.id.details_aty_name);
        moneyTv = (TextView) findViewById(R.id.details_aty_money);
        contentTv = (TextView) findViewById(R.id.details_aty_present);

    }

    @Override
    protected void initData() {

        initFragments();


        detailAdapter = new MyHotDetailAdapter(getSupportFragmentManager());
        detailAdapter.setFragments(fragments);
        viewPager.setAdapter(detailAdapter);
        tabLayout.setupWithViewPager(viewPager);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest("http://api.liwushuo.com/v2/items/" + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                bBean = gson.fromJson(response, BBean.class);
                nameTv.setText(bBean.getData().getName());
                moneyTv.setText("¥" + bBean.getData().getPrice());
                contentTv.setText(bBean.getData().getDescription());
//                mySelectLvAdapter.setSelectLvBean(selectLvBean);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);

        //轮播图**
        dotLayout.removeAllViews();
        carouselView();

        if (isAutoplay) {
            startPlay();
        }


        imgOne = (ImageView) inflater.inflate(R.layout.item_carousel_frm_select, null);
        imgTwo = (ImageView) inflater.inflate(R.layout.item_carousel_frm_select, null);
        imgThree = (ImageView) inflater.inflate(R.layout.item_carousel_frm_select, null);


        imgOne.setBackgroundResource(R.mipmap.ic_launcher);
        imgTwo.setBackgroundResource(R.mipmap.ic_launcher);
        imgThree.setBackgroundResource(R.mipmap.ic_launcher);

        list.add(imgOne);
        list.add(imgTwo);
        list.add(imgThree);
        myHotDetailsPagerAdapter = new MyHotDetailsPagerAdapter((ArrayList) list);
        myviewPager.setAdapter(myHotDetailsPagerAdapter);
        myviewPager.setCurrentItem(0);
        myviewPager.setOnPageChangeListener(new MyPageChangeListener());
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new PhotoShowFrm());
        fragments.add(new MyFragment());
    }


    //轮播图**
    private void carouselView() {
        dotViewList = new ArrayList<ImageView>();
        list = new ArrayList<ImageView>();

        for (int i = 0; i < 3; i++) {
            ImageView dotView = new ImageView(this);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ActionBar.LayoutParams(
                    ActionBar.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));

            params.leftMargin = 15;//设置小圆点的外边距
            params.rightMargin = 15;

            params.height = 40;//设置小圆点的大小
            params.width = 40;

            if (i == 0) {
                dotView.setBackgroundResource(R.mipmap.ic_launcher);
            } else {
                dotView.setBackgroundResource(R.mipmap.ic_launcher);
            }
            dotLayout.addView(dotView, params);

            dotViewList.add(dotView);//动态添加了3个小圆点
        }
    }

    //轮播图**开始轮播图切换
    private void startPlay() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4, TimeUnit.SECONDS);
        //根据他的参数说明，第一个参数是执行的任务，第二个参数是第一次执行的间隔，第三个参数是执行任务的周期
    }


    //轮播图** 执行轮播的切换任务
    private class SlideShowTask implements Runnable {
        @Override
        public void run() {
            synchronized (myviewPager) {
                currentItem = (currentItem + 1) % list.size();
                handler.sendEmptyMessage(100);
            }
        }
    }

    /**
     * ViewPager的监听器
     * 当ViewPager中页面的状态发生改变时调用
     */
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        boolean isAutoPlay = false;

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
            switch (arg0) {
                case 1:// 手势滑动，空闲中
                    isAutoPlay = false;
                    System.out.println(" 手势滑动，空闲中");
                    break;
                case 2:// 界面切换中
                    isAutoPlay = true;
                    System.out.println(" 界面切换中");
                    break;
                case 0:// 滑动结束，即切换完毕或者加载完毕
                    // 当前为最后一张，此时从右向左滑，则切换到第一张
                    if (myviewPager.getCurrentItem() == myviewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                        myviewPager.setCurrentItem(0);
                        System.out.println(" 滑动到最后一张");
                    }
                    // 当前为第一张，此时从左向右滑，则切换到最后一张
                    else if (myviewPager.getCurrentItem() == 0 && !isAutoPlay) {
                        myviewPager.setCurrentItem(myviewPager.getAdapter().getCount() - 1);
                        System.out.println(" 滑动到第一张");
                    }
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onPageSelected(int pos) {
            // TODO Auto-generated method stub
            //这里面动态改变小圆点的被背景，来实现效果
            currentItem = pos;
            for (int i = 0; i < dotViewList.size(); i++) {
                if (i == pos) {
                    ((View) dotViewList.get(pos)).setBackgroundResource(R.mipmap.point);
                } else {
                    ((View) dotViewList.get(i)).setBackgroundResource(R.mipmap.smallpoint);
                }
            }

        }
    }
}
