package com.jc.yooyoplus.gank.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jc.yooyoplus.common.view.MultipleStatusView;
import com.jc.yooyoplus.gank.R;
import com.jc.yooyoplus.gank.base.BaseFragment;
import com.jc.yooyoplus.gank.mvp.contract.XianduContract;
import com.jc.yooyoplus.gank.mvp.model.bean.CategoriesBean;
import com.jc.yooyoplus.gank.mvp.model.bean.XianduListBean;
import com.jc.yooyoplus.gank.mvp.presenter.XianduPresenter;
import com.jc.yooyoplus.gank.ui.adapter.XianduAdapter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class XianduFragment extends BaseFragment implements XianduContract.View {

    private XianduPresenter mPresenter;

    private List<XianduListBean.ResultsBean> resultsBeanList = new ArrayList<XianduListBean.ResultsBean>();

    private XianduAdapter mAdapter;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    private int size = 10;

    private int page = 1;

    private boolean isRefresh = false;

    private boolean loadingMore = false;

    private SmartRefreshLayout mSmartRefreshLayout;

    private RecyclerView mRecyclerView;

    private MultipleStatusView mMultipleStatusView;

    public static XianduFragment getInstance(String type) {
        XianduFragment fragment = new XianduFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        fragment.setType(type);
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.gank_fragment_xiandu;
    }

    @Override
    protected void initView(View rootView) {
        mAdapter = new XianduAdapter(getActivity(), resultsBeanList, R.layout.gank_item_xiandu);
        mRecyclerView = rootView.findViewById(R.id.gank_fragment_xiandu_rv);
        mMultipleStatusView = rootView.findViewById(R.id.gank_fragment_xiandu_msv);
        // 定义一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //设置 Header 为 Material风格
        mSmartRefreshLayout = rootView.findViewById(R.id.gank_fragment_xiandu_srl);
        mSmartRefreshLayout.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(false));
        mSmartRefreshLayout.setRefreshFooter(new FalsifyFooter(getActivity()));
        //下拉刷新
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                isRefresh = true;
                page = 1;
                mPresenter.requestXianduList(type, size, page);
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //在屏幕中可见的item数量
                    int childCount = mRecyclerView.getChildCount();
                    LinearLayoutManager manager = (LinearLayoutManager)mRecyclerView.getLayoutManager();
                    //已加载的item的数量
                    int itemCount = manager.getItemCount();
                    //在屏幕中可见的item的第一个
                    int firstVisibleItem = manager.findFirstVisibleItemPosition();
                    if (firstVisibleItem + childCount == itemCount) {
                        if (!loadingMore) {
                            loadingMore = true;
                            page++;
                            mPresenter.requestMoreXianduList(type, size, page);
                        }
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        mPresenter = new XianduPresenter();
        mPresenter.attachView(this);
        if(!type.isEmpty()) {
            mPresenter.requestXianduList(type, size, page);
        }
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void setXianduCategories(CategoriesBean categories) {

    }

    @Override
    public void setXianduList(XianduListBean xianduList) {
        if(mMultipleStatusView != null) {
            mMultipleStatusView.showContent();
        }
        if(page == 1) mAdapter.clearItemList();
        mAdapter.addItemData(xianduList.getResults());

    }

    @Override
    public void setMoreXianduList(XianduListBean xianduList) {
        loadingMore = false;
        mAdapter.addItemData(xianduList.getResults());
    }

    @Override
    public void showLoading() {
        if(!isRefresh) {
            isRefresh = false;
            mMultipleStatusView.showLoading();
        }
    }

    @Override
    public void dismissLoading() {
        mSmartRefreshLayout.finishRefresh();
    }
}
