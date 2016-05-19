package com.hx.mypresent.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 16/5/17.
 */
public class MySortViewpagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] titles = {"攻略", "礼物"};

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
//        notifyDataSetChanged();
    }

    public MySortViewpagerAdapter(FragmentManager fm) {
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
