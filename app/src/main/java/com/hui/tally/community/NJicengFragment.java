package com.hui.tally.community;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hui.tally.R;

public class NJicengFragment extends BaseFragment{
    private static final String TAG = NJicengFragment.class.getSimpleName();

    @Override
    public View initView() {
        Log.i(TAG, "财经的视图被实例化了");
        View view = View.inflate(getContext(), R.layout.news_caijing, null);
        TextView tv2 = view.findViewById(R.id.tv2);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "基层的Fragment的数据被初始化了");

    }

}

