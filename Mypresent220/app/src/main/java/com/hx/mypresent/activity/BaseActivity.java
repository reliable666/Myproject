package com.hx.mypresent.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

/**
 * Created by leisure on 16/5/8.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayout());
        initView();
        initData();
    }
    /**
     *  @硕哥告诉你
     *  16/5/9下午2:19
     *  这是加载布局的抽象方法
     */
    protected abstract  int getLayout();
    /**
     *  @硕哥告诉你
     *  16/5/9下午2:19
     *  这是加载数据的方法
     */
    protected abstract void initData();
    /**
     *  @硕哥告诉你
     *  16/5/9下午2:19
     *  这是加载组件的方法
     */
    protected  abstract  void initView();


    /**
     *  @硕哥告诉你
     *  16/5/9下午2:20
     *  这个方法使组件实例化不需要转型
     *
     *  使用方式:
     *  TextView textView = bindView(R.id.tv);
     *  这样使用这个方法的时候是不需要强转的
     */
    protected <T extends View> T bindView(int id){
        return (T) findViewById(id);
    }
}
