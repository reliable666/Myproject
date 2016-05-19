package com.hx.mypresent.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dllo on 16/4/8.
 */
public abstract class BaseFragment extends Fragment {
    //Context对象,,方便toast等操作
    //它实际上就是Fragment依附的Activity
    public Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    //要求使用该基类的Fragment重写这个方法
    //来设置布局
    public abstract int setLayout();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(setLayout(), container, false);
    }


    //该方法是专门用来初始化组件,参数中的view就是在oncreateview中返回的view

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);
        initView(view); //调用初始化组件的方法
    }

    public abstract void initView(View view);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();//让子类在这里初始化数据
    }

    //初始化数据和逻辑代码也写在这
    public abstract void initData();

}
