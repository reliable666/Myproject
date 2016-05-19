package com.hx.mypresent.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.hx.mypresent.R;
import com.hx.mypresent.activity.SearchActivity;
import com.hx.mypresent.adapter.MySortViewpagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/9.
 */
public class SortFragment extends BaseFragment implements View.OnClickListener {
    private MySortViewpagerAdapter mySortViewpagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragments;
    private ImageView jumpIv;

    @Override
    public int setLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    public void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.frm_sort_vp);
        tabLayout = (TabLayout) view.findViewById(R.id.frm_sort_tab);
        jumpIv = (ImageView) view.findViewById(R.id.frm_sort_iv);
    }

    @Override
    public void initData() {

        initFragments();
        mySortViewpagerAdapter = new MySortViewpagerAdapter(getChildFragmentManager());
        mySortViewpagerAdapter.setFragments(fragments);

        viewPager.setAdapter(mySortViewpagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        jumpIv.setOnClickListener(this);
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new SortStategyFrm());
        fragments.add(new SortGiftFragment());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frm_sort_iv:
                Intent intent = new Intent(context, SearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
