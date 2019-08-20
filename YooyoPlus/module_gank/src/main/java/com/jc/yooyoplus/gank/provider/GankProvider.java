package com.jc.yooyoplus.gank.provider;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jc.yooyoplus.common.provider.IGankProvider;
import com.jc.yooyoplus.gank.ui.fragment.GirlFragment;
import com.jc.yooyoplus.gank.ui.fragment.TestFragment;

@Route(path = "/gank/main",name = "干货")
public class GankProvider implements IGankProvider {
    @Override
    public Fragment getTestFragment() {
        return TestFragment.getInstance();
    }

    @Override
    public Fragment getGirlFragment() {
        return GirlFragment.getInstance();
    }

    @Override
    public void init(Context context) {

    }
}
