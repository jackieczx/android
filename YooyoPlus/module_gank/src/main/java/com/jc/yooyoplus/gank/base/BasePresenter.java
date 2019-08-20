package com.jc.yooyoplus.gank.base;

import java.io.PrintStream;

public class BasePresenter<T extends IBaseView> implements IPresenter<T>{

    public T mRootView;

    @Override
    public void attachView(T mRootView) {
        this.mRootView = mRootView;
    }

    @Override
    public void detachView() {
        mRootView = null;
    }

    /**
     * 判断是否注入View
     * @return
     */
    private boolean isViewAttached() {
        return mRootView != null;
    }

    private static final String MvpViewNotAttachedExceptionMsg = "Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter";

    public void checkViewAttached() {
        if(!isViewAttached()) {
            throw new RuntimeException(MvpViewNotAttachedExceptionMsg);
        }
    }

}




