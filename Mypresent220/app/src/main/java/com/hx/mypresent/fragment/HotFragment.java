package com.hx.mypresent.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hx.mypresent.R;
import com.hx.mypresent.activity.HotDetailsAty;
import com.hx.mypresent.activity.SearchActivity;
import com.hx.mypresent.adapter.MyHotRvAdapter;
import com.hx.mypresent.bean.HotRvBean;
import com.hx.mypresent.myinterface.MyRvOnclickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/9.
 */
public class HotFragment extends BaseFragment implements MyRvOnclickListener, View.OnClickListener {
    private TextView titleText;
    private RecyclerView recyclerView;
    private MyHotRvAdapter hotRvAdapter;
    List<String> datas = new ArrayList<>();
    private HotRvBean hotRvBean;
    private ImageView jumpIv;

    @Override
    public int setLayout() {
        return R.layout.fragment_hot;

    }

    @Override
    public void initView(View view) {
        titleText = (TextView) view.findViewById(R.id.include_title);
        recyclerView = (RecyclerView) view.findViewById(R.id.frm_hot_rv);
        jumpIv = (ImageView) view.findViewById(R.id.inclde_aty_iv);

    }

    @Override
    public void initData() {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        hotRvAdapter = new MyHotRvAdapter(context);
//        for (int i = 'A'; i < 'z'; i++) {
//            datas.add(String.valueOf((char)i));
//        }
//        hotRvAdapter.setData(datas);

        titleText.setText("精选");
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest("http://api.liwushuo.com/v2/items?gender=1&generation=4&limit=50&oddset=0", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                hotRvBean = gson.fromJson(response, HotRvBean.class);
                hotRvAdapter.setHotRvBean(hotRvBean);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);
        recyclerView.setAdapter(hotRvAdapter);

        hotRvAdapter.setMyRvOnclickListener(this);

        jumpIv.setOnClickListener(this);
    }


    @Override
    public void myOnclick(int id) {
        Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("id", id);
        intent.setClass(context, HotDetailsAty.class);
        startActivity(intent);
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
