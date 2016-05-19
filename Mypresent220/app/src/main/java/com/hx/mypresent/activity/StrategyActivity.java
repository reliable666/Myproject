package com.hx.mypresent.activity;

import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hx.mypresent.R;

/**
 * Created by dllo on 16/5/12.
 */
public class StrategyActivity extends BaseActivity {
    private WebView webView;


    @Override
    protected int getLayout() {
        return R.layout.activity_strategy;
    }

    @Override
    protected void initView() {
        webView = (WebView) findViewById(R.id.frm_strategy_web_view);
    }

    @Override
    public void initData() {
        //接受到网址的id
        Intent intent = getIntent();
        int url = intent.getIntExtra("url", 0);
        String imageUrl = "http://www.liwushuo.com/posts/" + url;


        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(imageUrl);
    }


}
