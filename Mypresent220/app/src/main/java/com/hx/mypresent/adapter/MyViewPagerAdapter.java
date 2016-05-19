package com.hx.mypresent.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hx.mypresent.bean.TabBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/9.
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter {
    private TabBean tabBean;
    private List<Fragment> fragments;
    private ArrayList<String> titles;

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
        notifyDataSetChanged();
    }




//    private String[] titles = {tabBean.getData().getCandidates().get(0).getName()};

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles == null ? 0: titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }


}
