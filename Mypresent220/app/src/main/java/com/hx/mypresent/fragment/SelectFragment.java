package com.hx.mypresent.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hx.mypresent.R;
import com.hx.mypresent.activity.StrategyActivity;
import com.hx.mypresent.adapter.MySelectLvAdapter;
import com.hx.mypresent.adapter.MySelectPagerAdapter;
import com.hx.mypresent.adapter.MySelectRvAdapter;
import com.hx.mypresent.bean.SelectCarouselBean;
import com.hx.mypresent.bean.SelectLvBean;
import com.hx.mypresent.bean.SelectBean;
import com.hx.mypresent.bean.SelectRvBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/9.
 */
public class SelectFragment extends BaseFragment {
    private ListView listView;
    private List<SelectBean> selectBeans;
    private MySelectLvAdapter mySelectLvAdapter;
    private SelectLvBean selectLvBean;//下面滑动的listView的解析类
    private SelectCarouselBean selectCarouselBean;//轮播解析类
    private SelectRvBean selectRvBean;//小滑动Rv解析类
    ImageView imgOne, imgTwo, imgThree,imgfour;

    //轮播图**
    private MySelectPagerAdapter mySelectPagerAdapter;
    private ViewPager myviewPager;
    //用于小圆点图片
    private List<ImageView> dotViewList;
    //用于存轮播效果图片
    private List<ImageView> list;
    LinearLayout dotLayout;
    //当前页面
    private int currentItem = 0;
    //是否自动轮播
    boolean isAutoplay = true;
    private ScheduledExecutorService scheduledExecutorService;


    private RecyclerView  recyclerView;
    private MySelectRvAdapter mySelectRvAdapter;


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

    //
    //list里面的假数据
    private void initList() {
        selectBeans = new ArrayList<>();
   for (int i = 0; i < 100; i++) {
            selectBeans.add(new SelectBean(R.mipmap.ic_launchers));
        }
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_select;
    }

    @Override
    public void initView(View view) {
        listView = (ListView) view.findViewById(R.id.select_frm_lv);
    }

    @Override
    public void initData() {
        //加载头布局的抽出去的方法
        headerView();

        mySelectLvAdapter = new MySelectLvAdapter(context);

        //listView的网络拉取
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest("http://api.liwushuo.com/v2/channels/1/items?ad=2&gender=1&generation=4&limit=20&set=0",
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                selectLvBean = gson.fromJson(response, SelectLvBean.class);

                mySelectLvAdapter.setSelectLvBean(selectLvBean);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });
        //轮播图的网络拉取
        StringRequest requestShuffling = new StringRequest("http://api.liwushuo.com/v2/banners?channel=ANDROID",
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gsonSf = new Gson();
                selectCarouselBean = gsonSf.fromJson(response, SelectCarouselBean.class);
                Picasso.with(context).load(selectCarouselBean.getData().getBanners().get(0).getWebp_url()).placeholder(R.mipmap.lolo).error(R.mipmap.lolo).into(imgOne);
                Picasso.with(context).load(selectCarouselBean.getData().getBanners().get(1).getWebp_url()).placeholder(R.mipmap.lolo).error(R.mipmap.lolo).into(imgTwo);
                Picasso.with(context).load(selectCarouselBean.getData().getBanners().get(2).getWebp_url()).placeholder(R.mipmap.lolo).error(R.mipmap.lolo).into(imgThree);
                Picasso.with(context).load(selectCarouselBean.getData().getBanners().get(3).getWebp_url()).placeholder(R.mipmap.lolo).error(R.mipmap.lolo).into(imgfour);
//                Log.d("SelectFragment", "selectCarouselBean:***************" + selectCarouselBean);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //拉取小滑动的数据
        StringRequest requestSliding = new StringRequest("http://api.liwushuo.com/v2/secondary_banners?gender=1&generation=2", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gsonSd = new Gson();
                selectRvBean = gsonSd.fromJson(response, SelectRvBean.class);
                recyclerView.setLayoutManager(new GridLayoutManager(context, selectRvBean.getData().getSecondary_banners().size()));
                mySelectRvAdapter.setSelectRvBean(selectRvBean);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
        requestQueue.add(requestShuffling);
        requestQueue.add(requestSliding);

        //加载listView
        listView.setDivider(null);
        listView.setDividerHeight(0);
        listView.setAdapter(mySelectLvAdapter);
        //点击事件 减2获取到当前的位置
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d("lalala","***"+position);
                Intent intent = new Intent();
                intent.putExtra("url", selectLvBean.getData().getItems().get(position - 2).getId());
                intent.setClass(context, StrategyActivity.class);
                startActivity(intent);


            }
        });


//----------------**

        //轮播图**
        dotLayout.removeAllViews();
        carouselView();

        if (isAutoplay) {
            startPlay();
        }



        imgOne = (ImageView) LayoutInflater.from(context).inflate(R.layout.item_carousel_frm_select, null);
        imgTwo = (ImageView) LayoutInflater.from(context).inflate(R.layout.item_carousel_frm_select, null);
        imgThree = (ImageView) LayoutInflater.from(context).inflate(R.layout.item_carousel_frm_select, null);
        imgfour = (ImageView) LayoutInflater.from(context).inflate(R.layout.item_carousel_frm_select, null);


//        imgTwo.setBackgroundResource(R.mipmap.ic_launcher);
        list.add(imgOne);
        list.add(imgTwo);
        list.add(imgThree);
        list.add(imgfour);

        mySelectPagerAdapter = new MySelectPagerAdapter((ArrayList) list);
        myviewPager.setAdapter(mySelectPagerAdapter);
        myviewPager.setCurrentItem(0);
        myviewPager.setOnPageChangeListener(new MyPageChangeListener());


    }


    private void headerView() {

        View headerCarouselView = getLayoutInflater(null).inflate(R.layout.fragment_select_carousel, null);

        listView.addHeaderView(headerCarouselView);
        myviewPager = (ViewPager) headerCarouselView.findViewById(R.id.select_frm_vp);
        dotLayout = (LinearLayout) headerCarouselView.findViewById(R.id.select_frm_dotLayout);

        View slideView = getLayoutInflater(null).inflate(R.layout.fragment_select_slide, null);
        recyclerView = (RecyclerView) slideView.findViewById(R.id.frm_select_slide_rv);
        mySelectRvAdapter = new MySelectRvAdapter(context);
        recyclerView.setAdapter(mySelectRvAdapter);
        listView.addHeaderView(slideView);

    }

    //轮播图**
    private void carouselView() {
        dotViewList = new ArrayList<ImageView>();
        list = new ArrayList<ImageView>();

        for (int i = 0; i < 4; i++) {
            ImageView dotView = new ImageView(context);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ActionBar.LayoutParams(
                    ActionBar.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));

            params.leftMargin = 15;//设置小圆点的外边距
            params.rightMargin = 15;

            params.height = 20;//设置小圆点的大小
            params.width = 20;

            if (i == 0) {
                dotView.setBackgroundResource(R.mipmap.point);
            } else {
                dotView.setBackgroundResource(R.mipmap.point);
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


