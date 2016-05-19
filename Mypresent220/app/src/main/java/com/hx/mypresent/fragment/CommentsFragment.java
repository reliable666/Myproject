package com.hx.mypresent.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hx.mypresent.R;
import com.hx.mypresent.adapter.MyCommentsAdapter;
import com.hx.mypresent.bean.CBean;

/**
 * Created by dllo on 16/5/14.
 */
public class CommentsFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private CBean cBean;
    private MyCommentsAdapter commentsAdapter;
    @Override
    public int setLayout() {
        return R.layout.fragment_comments;
    }

    @Override
    public void initView(View view) {
       recyclerView = (RecyclerView) view.findViewById(R.id.frm_comments_rv);
    }

    @Override
    public void initData() {
       recyclerView.setLayoutManager(new LinearLayoutManager(context));
        commentsAdapter =new MyCommentsAdapter(context);

        Intent intent = getActivity().getIntent();
        int id = intent.getIntExtra("id", 0);

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest("http://api.liwushuo.com/v2/items/1041646/comments?limit=20&offset=20", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                cBean = gson.fromJson(response, CBean.class);
                commentsAdapter.setcBean(cBean);
                Log.d("haha"," "+cBean.getData().getComments().size());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);
        recyclerView.setAdapter(commentsAdapter);

    }
}
