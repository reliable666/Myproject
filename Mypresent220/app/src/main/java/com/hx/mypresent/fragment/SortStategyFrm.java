package com.hx.mypresent.fragment;

import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hx.mypresent.R;
import com.hx.mypresent.adapter.MySortStategyGvAdapter;
import com.hx.mypresent.adapter.MySortStategyLvAdapter;
import com.hx.mypresent.bean.SortStatrgyBean;
import com.hx.mypresent.util.VolleySinge;

/**
 * Created by dllo on 16/5/17.
 */
public class SortStategyFrm extends BaseFragment {


    private ListView listView;
    private GridView gridView;
    private MySortStategyLvAdapter mySortStategyLvAdapter;


    private SortStatrgyBean sortStatrgyBean;


    @Override
    public int setLayout() {
        return R.layout.frm_sort_stategy;
    }

    @Override
    public void initView(View view) {
        listView = (ListView) view.findViewById(R.id.sort_stategy_frm_lv);
        gridView = (GridView) view.findViewById(R.id.item_strategy_gv);
    }

    @Override
    public void initData() {


        mySortStategyLvAdapter = new MySortStategyLvAdapter(context);
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//
//        StringRequest request = new StringRequest("http://api.liwushuo.com/v2/channels/1/items?ad=2&gender=1&generation=4&limit=20&set=0",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        Gson gson = new Gson();
////                        selectLvBean = gson.fromJson(response, SelectLvBean.class);
//                        sortStatrgyBean = gson.fromJson(response, SortStatrgyBean.class);
//
//                        Log.d("武器大师",sortStatrgyBean.getData().getChannel_groups()+" ");
////                        mySelectLvAdapter.setSelectLvBean(selectLvBean);
//                        mySortStategyLvAdapter.setSortStatrgyBean(sortStatrgyBean);
//                        listView.setAdapter(mySortStategyLvAdapter);
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        requestQueue.add(request);
        VolleySinge.addRequest("http://api.liwushuo.com/v2/channel_groups/all",
                SortStatrgyBean.class, new Response.Listener<SortStatrgyBean>() {
                    @Override
                    public void onResponse(SortStatrgyBean response) {
                        Log.d("数据获取", "response:" + response.getData().getChannel_groups());
                        mySortStategyLvAdapter.setSortStatrgyBean(response);
                        for (int i = 0; i < response.getData().getChannel_groups().get(0).getChannels().size(); i++) {

                        Log.d("SortStategyFrm", response.getData().getChannel_groups().get(0).getChannels().get(i).getName());
                        }
                        listView.setAdapter(mySortStategyLvAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("hsi", "失败"+error);
                    }
                });


    }
}
