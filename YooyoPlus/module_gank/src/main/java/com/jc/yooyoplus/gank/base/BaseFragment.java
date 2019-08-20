package com.jc.yooyoplus.gank.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jc.yooyoplus.common.view.MultipleStatusView;

public abstract class BaseFragment extends Fragment {
    /**
     * 视图是否加载完毕
     */
    private boolean isViewPrepare = false;
    /**
     * 数据是否加载过了
     */
    private boolean hasLoadData = false;
    /**
     * 多状态View的切换
     */
    protected MultipleStatusView mLayoutStatusView = null;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            lazyLoadDataIfPrepared();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewPrepare = true;
        initView(view);
        lazyLoadDataIfPrepared();
        //多种状态切换的view 重试点击事件
        if(mLayoutStatusView != null) mLayoutStatusView.setOnClickListener(mRetryClickListener);
    }

    private void lazyLoadDataIfPrepared() {
        if (getUserVisibleHint() && isViewPrepare && !hasLoadData) {
            lazyLoad();
            hasLoadData = true;
        }
    }

    public View.OnClickListener mRetryClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            lazyLoad();
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 加载布局
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化View
     */
    protected abstract void initView(View rootView);

    /**
     * 懒加载
     */
    protected abstract void lazyLoad();
}
