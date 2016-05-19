package com.hx.mypresent.fragment;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.hx.mypresent.R;
import com.hx.mypresent.adapter.MyDrawerApapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/9.
 */
public class MyFragment extends BaseFragment {
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private LinearLayout drawerView;
    private RecyclerView recyclerView;
    private MyDrawerApapter myDrawerApapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        //找到抽屉的组件id
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
//        drawerView = (LinearLayout) view.findViewById(R.id.drawer_view);
        recyclerView = (RecyclerView) view.findViewById(R.id.frm_my_draLayout_rv);

    }

    @Override
    public void initData() {
        //toolbar 点击弹出抽屉
        drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.open, R.string.close);
        drawerToggle.syncState();
//        drawerLayo ut.setOnDragListener((View.OnDragListener) drawerToggle);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        myDrawerApapter = new MyDrawerApapter(context);
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add(i + " ");
        }
        Log.d("无敌",datas+" ");

        myDrawerApapter.setDatas(datas);
        recyclerView.setAdapter(myDrawerApapter);
    }
}