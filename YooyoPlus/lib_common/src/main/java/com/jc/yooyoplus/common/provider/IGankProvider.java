package com.jc.yooyoplus.common.provider;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface IGankProvider extends IProvider {
    Fragment getTestFragment();
    Fragment getGirlFragment();
}
