package com.jc.yooyoplus.gank.base;

public interface IPresenter<T extends IBaseView> {

    //注入View
    void attachView(T mRootView);

    //卸载View，解除View的引用
    void detachView();
}
