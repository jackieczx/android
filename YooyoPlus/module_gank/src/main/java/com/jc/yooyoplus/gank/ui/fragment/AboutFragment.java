package com.jc.yooyoplus.gank.ui.fragment;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jc.yooyoplus.gank.R;
import com.jc.yooyoplus.gank.base.BaseFragment;

@Route(path = "/gank/about")
public class AboutFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.gank_fragment_about;
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected void lazyLoad() {

    }
}
