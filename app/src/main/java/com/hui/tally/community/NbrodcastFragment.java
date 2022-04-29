package com.hui.tally.community;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hui.tally.R;

public class NbrodcastFragment extends BaseFragment {
    private static final String TAG = NbrodcastFragment.class.getSimpleName();

    @Override
    public View initView() {
        Log.i(TAG, "广播的视图被实例化了");
        View view = View.inflate(getContext(), R.layout.news_broadcast, null);
        findView(view);
        return view;
    }

    private void findView(View view) {
        TextView tv2 = view.findViewById(R.id.tv2);

    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "广播的Fragment的数据被初始化了");

    }

}