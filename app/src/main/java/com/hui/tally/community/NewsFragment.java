package com.hui.tally.community;

import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hui.tally.R;
import com.hui.tally.adapter.NewsTabAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends BaseFragment {

    private static final String TAG = NewsFragment.class.getSimpleName();
    private ViewPager new_viewpager;
    private NewsTabAdapter newTabAdapter;
    private TabLayout tabLayout;

    @Override
    public View initView() {
        Log.i(TAG, "新闻的视图被实例化了");
        View view = View.inflate(getActivity(), R.layout.fragment_news, null);
        findView(view);
        return view;
    }

    private void findView(View view) {
        tabLayout = view.findViewById(R.id.tab_layout);
        new_viewpager = view.findViewById(R.id.view_pager);

    }


    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "新闻中心的Fragment的数据被初始化了");

        initNewsData();

    }

    private void initNewsData() {

        // 新闻分类标题导航栏
        String[] title = {"财经","电视","旅游","视频","广播"};
        //创建装载Fragment的列表
        List<Fragment> fragmentlist;
        // 初始化列表，并把创建的6个Fragment页面添加到列表中
        fragmentlist = new ArrayList<>();
        fragmentlist.add(new NJicengFragment());
        fragmentlist.add(new NbrodcastFragment());
        fragmentlist.add(new NTravelFragment());
        fragmentlist.add(new NvideoFragment());
        fragmentlist.add(new NbrodcastFragment());


        newTabAdapter = new NewsTabAdapter(getChildFragmentManager(),fragmentlist,title);
        // ViewPager与Adapter绑定
        new_viewpager.setAdapter(newTabAdapter);
        // TabLayout与ViewPager绑定
        tabLayout.setupWithViewPager(new_viewpager);

    }

}

