package com.jc.yooyoplus.gank.mvp.contract;

import com.jc.yooyoplus.gank.base.IBaseView;
import com.jc.yooyoplus.gank.base.IPresenter;
import com.jc.yooyoplus.gank.mvp.model.bean.CategoriesBean;
import com.jc.yooyoplus.gank.mvp.model.bean.XianduListBean;

public interface XianduContract {

    interface View extends IBaseView {
        //显示闲读主分类数据
        void setXianduCategories(CategoriesBean categories);
        //显示闲读数据列表
        void setXianduList(XianduListBean xianduList);
        void setMoreXianduList(XianduListBean xianduList);
    }

    interface Presenter extends IPresenter<View> {
        //获取闲读主分类数据
        void requestXianduCategories();
        //获取闲读数据列表
        void requestXianduList(String id, int count, int page);
        //获取更多闲读数据列表
        void requestMoreXianduList(String id, int count, int page);
    }
}
