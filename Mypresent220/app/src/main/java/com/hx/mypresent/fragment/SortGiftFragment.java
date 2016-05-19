package com.hx.mypresent.fragment;

import android.graphics.Color;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.hx.mypresent.R;
import com.hx.mypresent.adapter.GiftAdapter;
import com.hx.mypresent.adapter.LeftAdapter;
import com.hx.mypresent.bean.GiftBean;
import com.hx.mypresent.util.GsonRequest;
import com.hx.mypresent.util.VolleySinge;

import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView;

/**
 * Created by dllo on 16/5/18.
 */
public class SortGiftFragment extends BaseFragment {
    private ListView leftListView;
    private LeftAdapter leftAdapter;
    private GiftBean giftBean;
    private PinnedHeaderListView rightListView;
    private boolean isScroll = true;
    private GiftAdapter adapter;


    @Override
    public int setLayout() {
        return R.layout.fragment_sort_gift;
    }

    @Override
    public void initView(View view) {
        leftListView = (ListView) view.findViewById(R.id.frm_sort_gift_left_lv);
        rightListView = (PinnedHeaderListView) view.findViewById(R.id.frm_sort_gift_right_lv);
    }


    @Override
    public void initData() {

        VolleySinge.addRequest("http://api.liwushuo.com/v2/item_categories/tree", GiftBean.class, new Response.Listener<GiftBean>() {
                    @Override
                    public void onResponse(final GiftBean response) {
                        giftBean = response;
                        leftAdapter = new LeftAdapter(context, response);
                        leftListView.setAdapter(leftAdapter);
                        adapter = new GiftAdapter(context);
                        adapter.setGiftBean(giftBean);
                        rightListView.setAdapter(adapter);
                        leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                isScroll = false;
                                for (int i = 0; i < leftListView.getChildCount(); i++) {
                                    if (i == position) {
                                        leftListView.getChildAt(i).setBackgroundColor(Color.rgb(255, 255, 255));
                                    } else {
                                        leftListView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                                    }
                                }
                                int rightSection = 0;
                                for (int i = 0; i < position; i++) {
                                    rightSection += adapter.getCountForSection(i) + 1;
                                }
                                rightListView.setSelection(rightSection);
                            }
                        });
                        rightListView.setOnScrollListener(new AbsListView.OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(AbsListView view, int scrollState) {

                            }

                            @Override
                            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                                if (isScroll) {
//                            for (int i = 0; i < leftListView.getCount(); i++) {
//                                if (i == adapter.getSectionForPosition(firstVisibleItem)) {
//                                    leftListView.smoothScrollToPosition(i);
////                                    leftListView.getChildAt().setBackgroundColor(Color.rgb(255, 255, 255));
//                                    //Log.d("GiftFragment", "i:" + i);
//                                } else {
//                                    if (i < leftListView.getChildCount()) {
//                                        leftListView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
//                                    }
//                                }
//                            }

//                                    leftListView.smoothScrollToPosition(adapter.getSectionForPosition(firstVisibleItem));
//                                    leftAdapter.setSelectPos(adapter.getSectionForPosition(firstVisibleItem));

                                    for (int i = 0; i < leftListView.getChildCount(); i++) {
                                        if (i == adapter.getSectionForPosition(firstVisibleItem)){
                                            leftListView.getChildAt(i).setBackgroundColor(Color.WHITE);
                                        } else {
                                            leftListView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                                        }

                                    }

                                } else {
                                    isScroll = true;
                                }
                            }
                        });


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        );


    }

   // @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.menu_gift_fragment, menu);
//    }
}



