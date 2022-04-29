package com.hui.tally.community;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import com.hui.tally.R;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private Banner banner;
    private EditText edt_search;
    private List<BannerBean.RowsDTO> list;

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_home,null);
        banner = (Banner) view.findViewById(R.id.banner);
        edt_search = (EditText) view.findViewById(R.id.edt_search);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        initEditService();  //初始化搜索框
        initBanner();      // 初始化轮播图

    }

    private void initBanner() {
        //网络加载图片

        List<BannerBean.RowsDTO> list = new ArrayList<>();
        list.add(new BannerBean.RowsDTO("https://img.zcool.cn/community/013de756fb63036ac7257948747896.jpg", "1"));
        list.add(new BannerBean.RowsDTO("https://image14.m1905.cn/uploadfile/2018/0906/thumb_1_1380_460_20180906040153529630.jpg", "2"));
        list.add(new BannerBean.RowsDTO("https://n.sinaimg.cn/finance/801e2f9c/20220402/WeiBiaoTi-15.jpg", "3"));
        list.add(new BannerBean.RowsDTO("http://img3.caijing.com.cn/retain/index/images/logo.gif", "4"));
        banner.setAdapter(new BannerImageAdapter<BannerBean.RowsDTO>(list) {
            @Override
            public void onBindView(BannerImageHolder holder, BannerBean.RowsDTO data, int position, int size) {
                //BannerImageHolder 利用banner实现图片加载,也可自己实现图片加载
                Glide.with(getActivity())
                        .load(data.getImgUrl())
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                        .into(holder.imageView);
            }
        }).addBannerLifecycleObserver(this)   //添加生命周期观察者
                .setIndicator(new CircleIndicator(getActivity()))
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object o, int position) {
                        Intent intent = new Intent(getActivity(), BannerWebView.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("url",list.get(position).getImgUrl());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
                    }
                });
    }

    private void initEditService() {
        edt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String search = edt_search.getText().toString();
                    Intent intent = new Intent(getActivity(), NewSearchActivity.class);
                    intent.putExtra("search",search);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

}
