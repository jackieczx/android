package com.jc.yooyoplus.gank.ui.fragment;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jc.yooyoplus.gank.R;
import com.jc.yooyoplus.gank.base.BaseFragment;

@Route(path = "/gank/test")
public class TestFragment extends BaseFragment {

    public static TestFragment getInstance() {
        return new TestFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.gank_fragment_test;
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected void lazyLoad() {

    }
}
