package com.hui.tally.community;

import android.view.View;
import android.widget.TextView;

import com.hui.tally.R;

public class ServiceFragment extends BaseFragment {

    private TextView tv;


    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_service,null);
        tv = (TextView) view.findViewById(R.id.tv);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }


}

