package com.hui.tally.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;


public class NewsTabAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private String[] titles;

    public NewsTabAdapter(@NonNull FragmentManager fm,List<Fragment> fragmentList,String[] titles) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titles = titles;
    }


    /**
     * 返回当前的fragment
     * @param position: 当前页面的位置
     * @return
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);
    }

    /**
     * fragment中的个数
     */
    @Override
    public int getCount() {

        return fragmentList.size();
    }

    /**
     * 返回当前的标题
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}


