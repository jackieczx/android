package com.jc.yooyoplus.gank.ui.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.jc.yooyoplus.common.CommonUtils;
import com.jc.yooyoplus.common.EvenItemDecoration;
import com.jc.yooyoplus.common.view.MultipleStatusView;
import com.jc.yooyoplus.gank.R;
import com.jc.yooyoplus.gank.base.BaseFragment;
import com.jc.yooyoplus.gank.mvp.contract.GirlContract;
import com.jc.yooyoplus.gank.mvp.model.bean.GirlListBean;
import com.jc.yooyoplus.gank.mvp.presenter.GirlPresenter;
import com.jc.yooyoplus.gank.ui.adapter.GirlAdapter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/gank/girl")
public class GirlFragment extends BaseFragment implements GirlContract.View {

    private GirlPresenter mPresenter;

    private GirlAdapter mAdapter;

    private List<GirlListBean.ResultsBean> girlList = new ArrayList<GirlListBean.ResultsBean>();

    private SmartRefreshLayout mSmartRefreshLayout;

    private RecyclerView mRecyclerView;

    private MultipleStatusView mMultipleStatusView;

    private int size = 12;

    private int page = 1;

    private boolean isRefresh = false;

    private boolean loadingMore = false;

    public static GirlFragment getInstance() {
        return new GirlFragment();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.gank_fragment_girl;
    }

    @Override
    protected void initView(View rootView) {

        mAdapter = new GirlAdapter(getActivity(), girlList, R.layout.gank_item_girl);

        mRecyclerView = rootView.findViewById(R.id.gank_fragment_girl_rv);
        mMultipleStatusView = rootView.findViewById(R.id.gank_fragment_girl_msv);

        // 定义一个表格布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.addItemDecoration(new EvenItemDecoration(CommonUtils.dip2px(getContext(), 4), 3));
        mRecyclerView.setAdapter(mAdapter);

        //设置 Header 为 Material风格
        mSmartRefreshLayout = rootView.findViewById(R.id.gank_fragment_girl_srl);
        mSmartRefreshLayout.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(false));

        //下拉刷新
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                isRefresh = true;
                page = 1;
                mPresenter.requestGirlList(size, page);
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //在屏幕中可见的item数量
                    int childCount = mRecyclerView.getChildCount();
                    GridLayoutManager manager = (GridLayoutManager)mRecyclerView.getLayoutManager();
                    //已加载的item的数量
                    int itemCount = manager.getItemCount();
                    //在屏幕中可见的item的第一个
                    int firstVisibleItem = manager.findFirstVisibleItemPosition();
                    if (firstVisibleItem + childCount == itemCount) {
                        if (!loadingMore) {
                            loadingMore = true;
                            page++;
                            mPresenter.requestMoreGirlList(size, page);
                        }
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        mPresenter = new GirlPresenter();
        mPresenter.attachView(this);
        mPresenter.requestGirlList(size, page);

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void setGirlList(GirlListBean girlList) {
        if(mMultipleStatusView != null) {
            mMultipleStatusView.showContent();
        }
        if(page == 1) mAdapter.clearItemList();
        mAdapter.addItemData(girlList.getResults());
    }

    @Override
    public void setMoreGirlList(GirlListBean girlList) {
        loadingMore = false;
        mAdapter.addItemData(girlList.getResults());
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
