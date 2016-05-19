package com.hx.mypresent.fragment;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hx.mypresent.R;

/**
 * Created by dllo on 16/5/14.
 */
public class PhotoShowFrm extends BaseFragment {
    private WebView webView;

    @Override
    public int setLayout() {
        return R.layout.fragment_photo_show;
    }

    @Override
    public void initView(View view) {
        webView = (WebView) view.findViewById(R.id.frm_photo_show_webV);
    }

    @Override
    public void initData() {

        Intent intent = getActivity().getIntent();
        int id = intent.getIntExtra("id", 0);
        String webUrl = "http://www.liwushuo.com/items/"+id;

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(webUrl);
    }
}
