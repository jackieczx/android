package com.jc.yooyoplus.gank.mvp.contract;

import com.jc.yooyoplus.gank.base.IBaseView;
import com.jc.yooyoplus.gank.base.IPresenter;
import com.jc.yooyoplus.gank.mvp.model.bean.GirlListBean;

public interface GirlContract {

    interface View extends IBaseView {
        void setGirlList(GirlListBean girlList);
        void setMoreGirlList(GirlListBean girlList);
    }

    interface Presenter extends IPresenter<View> {
        void requestGirlList(int count, int page);
        void requestMoreGirlList(int count, int page);
    }
}
