package com.hx.mypresent.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hx.mypresent.R;
import com.hx.mypresent.adapter.MyViewPagerAdapter;
import com.hx.mypresent.bean.SelectLvBean;
import com.hx.mypresent.bean.TabBean;
import com.hx.mypresent.fragment.HomeFragment;
import com.hx.mypresent.fragment.HotFragment;
import com.hx.mypresent.fragment.MyFragment;
import com.hx.mypresent.fragment.SortFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private RadioButton homeRaBtn, hotRabtn, sortRaBtn, myRabtn;
    private TextView t1;




    @Override
    protected int getLayout() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        homeRaBtn = (RadioButton) findViewById(R.id.frm_home_rbtn);
        hotRabtn = (RadioButton) findViewById(R.id.frm_hot_rbtn);
        sortRaBtn = (RadioButton) findViewById(R.id.frm_sort_rbtn);
        myRabtn = (RadioButton) findViewById(R.id.frm_my_rbtn);

        homeRaBtn.setOnClickListener(this);
        sortRaBtn.setOnClickListener(this);
        hotRabtn.setOnClickListener(this);
        myRabtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.replace_view, new HomeFragment());
        transaction.commit();


    }


    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (v.getId()) {
            case R.id.frm_home_rbtn:

                transaction.replace(R.id.replace_view, new HomeFragment());
                break;
            case R.id.frm_hot_rbtn:

                transaction.replace(R.id.replace_view, new HotFragment());
                break;
            case R.id.frm_sort_rbtn:
                transaction.replace(R.id.replace_view, new SortFragment());
                break;
            case R.id.frm_my_rbtn:
                transaction.replace(R.id.replace_view, new MyFragment());
                break;
        }
        transaction.commit();
    }
}
