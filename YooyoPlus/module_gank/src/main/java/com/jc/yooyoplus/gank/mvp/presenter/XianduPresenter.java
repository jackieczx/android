package com.jc.yooyoplus.gank.mvp.presenter;

import com.jc.yooyoplus.gank.base.BasePresenter;
import com.jc.yooyoplus.gank.mvp.contract.XianduContract;
import com.jc.yooyoplus.gank.mvp.model.XianduModel;
import com.jc.yooyoplus.gank.mvp.model.bean.CategoriesBean;
import com.jc.yooyoplus.gank.mvp.model.bean.XianduListBean;
import com.jc.yooyoplus.gank.net.OkHttpException;
import com.jc.yooyoplus.gank.net.RequestParams;
import com.jc.yooyoplus.gank.net.ResponseCallback;

public class XianduPresenter extends BasePresenter<XianduContract.View> implements XianduContract.Presenter {

    private XianduModel xianduModel;

    public XianduPresenter() {
        xianduModel = new XianduModel();
    }

    @Override
    public void requestXianduCategories() {
        checkViewAttached();
        mRootView.showLoading();
        RequestParams params = new RequestParams();
        xianduModel.getCategories(params, new ResponseCallback<CategoriesBean>() {


            @Override
            public void onSuccess(CategoriesBean categoriesBean) {
                mRootView.setXianduCategories(categoriesBean);
            }

            @Override
            public void onFailure(OkHttpException failuer) {

            }
        });
    }

    @Override
    public void requestXianduList(String id, int count, int page) {
        checkViewAttached();
        mRootView.showLoading();
        xianduModel.getXianduList(id, count, page, new RequestParams(), new ResponseCallback<XianduListBean>() {

            @Override
            public void onSuccess(XianduListBean xianduListBean) {
                mRootView.dismissLoading();
                mRootView.setXianduList(xianduListBean);
            }

            @Override
            public void onFailure(OkHttpException failuer) {
                mRootView.dismissLoading();
            }
        });
    }

    @Override
    public void requestMoreXianduList(String id, int count, int page) {
        xianduModel.getXianduList(id, count, page, new RequestParams(), new ResponseCallback<XianduListBean>() {
            @Override
            public void onSuccess(XianduListBean xianduListBean) {
                mRootView.setMoreXianduList(xianduListBean);
            }

            @Override
            public void onFailure(OkHttpException failuer) {

            }
        });

    }
}
