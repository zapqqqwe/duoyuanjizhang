package com.hui.tally.community;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.hui.tally.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main_comActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tablayout;
    private List<Fragment> fragmentList;
    private String[] titles={"首页","服务","发布","新闻","用户中心"};
    private int[] unSele = {R.mipmap.main_home,R.mipmap.main_type,R.mipmap.main_cart,R.mipmap.main_community,R.mipmap.main_user};
    private int[] onSele = {R.mipmap.main_home_press,R.mipmap.main_type_press,R.mipmap.main_cart_press,R.mipmap.main_community_press,R.mipmap.main_user_press};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_com);

        viewPager = (ViewPager) findViewById(R.id.viewPager1);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        initData();
    }

    public void initData(){
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new ServiceFragment());
        fragmentList.add(new PostFragment());
        fragmentList.add(new NewsFragment());
        fragmentList.add(new UserFragment());

        MainTabAdapter mainTabAdapter = new MainTabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainTabAdapter);
        tablayout.setupWithViewPager(viewPager);

        for (int i=0;i<tablayout.getTabCount();i++){
            TabLayout.Tab tab = tablayout.getTabAt(i);
            tab.setCustomView(mainTabAdapter.getView(i));
        }

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                ImageView img = view.findViewById(R.id.img);
                TextView tv = view.findViewById(R.id.tv);
                String title = tv.getText().toString();
                if (title=="首页"){
                    img.setImageResource(onSele[0]);
                } else if (title=="服务") {
                    img.setImageResource(onSele[1]);
                } else if (title=="发布") {
                    img.setImageResource(onSele[2]);
                } else if (title=="新闻") {
                    img.setImageResource(onSele[3]);
                } else if (title=="用户中心") {
                    img.setImageResource(onSele[4]);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                ImageView img = view.findViewById(R.id.img);
                TextView tv = view.findViewById(R.id.tv);
                String title = tv.getText().toString();
                if (title=="首页"){
                    img.setImageResource(unSele[0]);
                } else if (title=="服务") {
                    img.setImageResource(unSele[1]);
                } else if (title=="发布") {
                    img.setImageResource(unSele[2]);
                } else if (title=="新闻") {
                    img.setImageResource(unSele[3]);
                } else if (title=="用户中心") {
                    img.setImageResource(unSele[4]);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }



    public class MainTabAdapter extends FragmentPagerAdapter {


        public MainTabAdapter(@NonNull FragmentManager fm) {
            super(Objects.requireNonNull(fm));
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if (position==0){
                return fragmentList.get(0);
            } else if (position==1){
                return fragmentList.get(1);
            }else if (position==2){
                return fragmentList.get(2);
            }else if (position==3){
                return fragmentList.get(3);
            }else if (position==4){
                return fragmentList.get(4);
            }
            return fragmentList.get(0);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        public View getView(int position) {
            View view = View.inflate(Main_comActivity.this,R.layout.main_tab_item,null);
            ImageView img = view.findViewById(R.id.img);
            TextView tv = view.findViewById(R.id.tv);
            if (tablayout.getTabAt(position).isSelected()) {
                img.setImageResource(onSele[position]);
            } else {
                img.setImageResource(unSele[position]);
            }
            tv.setText(titles[position]);
            tv.setTextColor(tablayout.getTabTextColors());
            return view;
        }
    }

}
