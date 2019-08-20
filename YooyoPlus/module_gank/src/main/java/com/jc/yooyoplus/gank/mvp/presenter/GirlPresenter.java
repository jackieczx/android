package com.jc.yooyoplus.gank.mvp.presenter;

import com.jc.yooyoplus.gank.base.BasePresenter;
import com.jc.yooyoplus.gank.mvp.contract.GirlContract;
import com.jc.yooyoplus.gank.mvp.model.GirlModel;
import com.jc.yooyoplus.gank.mvp.model.bean.GirlListBean;
import com.jc.yooyoplus.gank.net.OkHttpException;
import com.jc.yooyoplus.gank.net.RequestParams;
import com.jc.yooyoplus.gank.net.ResponseCallback;

public class GirlPresenter extends BasePresenter<GirlContract.View> implements GirlContract.Presenter {

    private GirlModel girlModel;

    public GirlPresenter() {
        girlModel = new GirlModel();
    }

    @Override
    public void requestGirlList(int count, int page) {
        checkViewAttached();
        mRootView.showLoading();
        RequestParams params = new RequestParams();
        girlModel.getGirlList(count, page, params, new ResponseCallback<GirlListBean>() {

            @Override
            public void onSuccess(GirlListBean girlListBean) {
                mRootView.dismissLoading();
                mRootView.setGirlList(girlListBean);
            }

            @Override
            public void onFailure(OkHttpException failuer) {
                mRootView.dismissLoading();
            }
        });
    }

    @Override
    public void requestMoreGirlList(int count, int page) {
        RequestParams params = new RequestParams();
        girlModel.getGirlList(count, page, params, new ResponseCallback<GirlListBean>() {

            @Override
            public void onSuccess(GirlListBean girlListBean) {
                mRootView.setMoreGirlList(girlListBean);
            }

            @Override
            public void onFailure(OkHttpException failuer) {
            }
        });
    }
}
