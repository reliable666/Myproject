package com.hx.mypresent.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hx.mypresent.R;
import com.hx.mypresent.activity.SearchActivity;
import com.hx.mypresent.adapter.MyViewPagerAdapter;
import com.hx.mypresent.bean.TabBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/9.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private List<Fragment> fragments;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView titleText;
    private MyViewPagerAdapter viewPagerAdapter;
    private TabBean tabBean;
    private ImageView jumpIv;

    @Override
    public int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.frm_home_viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.frm_home_tab);
        titleText = (TextView) view.findViewById(R.id.include_title);
        jumpIv = (ImageView) view.findViewById(R.id.inclde_aty_iv);

    }

    @Override
    public void initData() {
        initFragments();

        viewPagerAdapter = new MyViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPagerAdapter.setFragments(fragments);


        //网络获取到title的接口内容
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest("http://api.liwushuo.com/v2/channels/preset?gender=1&generation=4", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                tabBean = gson.fromJson(response, TabBean.class);

                ArrayList<String> titles = new ArrayList<>();
                for (int i = 0; i < tabBean.getData().getChannels().size(); i++) {
                    titles.add(tabBean.getData().getChannels().get(i).getName());
                }
                Log.d("HomeFragment", "titles:" + titles);
                viewPagerAdapter.setTitles(titles);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);


        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        jumpIv.setOnClickListener(this);
    }


    private void initFragments() {
        fragments = new ArrayList<>();

        fragments.add(new SelectFragment());
        fragments.add(new SelectFragment());
        fragments.add(new SelectFragment());
        fragments.add(new SelectFragment());
        fragments.add(new SelectFragment());
        fragments.add(new SelectFragment());
        fragments.add(new SelectFragment());
        fragments.add(new SelectFragment());
        fragments.add(new SelectFragment());
        fragments.add(new SelectFragment());
        fragments.add(new SelectFragment());
        fragments.add(new SelectFragment());
        fragments.add(new SelectFragment());
        fragments.add(new SelectFragment());
        fragments.add(new SelectFragment());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.inclde_aty_iv:
                Intent intent = new Intent(context, SearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
