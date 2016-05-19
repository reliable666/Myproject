package com.hx.mypresent.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 16/5/13.
 */
public class MyHotDetailAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] titles = {"图片介绍", "评论"};

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public MyHotDetailAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
