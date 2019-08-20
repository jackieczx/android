package com.jc.yooyoplus.gank.mvp.model;

import com.jc.yooyoplus.gank.mvp.model.bean.CategoriesBean;
import com.jc.yooyoplus.gank.mvp.model.bean.CategoriesChildBean;
import com.jc.yooyoplus.gank.mvp.model.bean.XianduListBean;
import com.jc.yooyoplus.gank.net.RequestMode;
import com.jc.yooyoplus.gank.net.RequestParams;
import com.jc.yooyoplus.gank.net.ResponseCallback;

public class XianduModel {

    //获取闲读的主分类
    public void getCategories(RequestParams params, ResponseCallback callback) {
        RequestMode.getRequest("http://gank.io/api/xiandu/categories", params, callback, CategoriesBean.class);
    }

    //获取闲读的子分类
    public void getCategoriesChild(String en_name, RequestParams params, ResponseCallback callback) {
        RequestMode.getRequest("http://gank.io/api/xiandu/category/" + en_name, params, callback, CategoriesChildBean.class);
    }

    //获取闲读数据列表
    public void getXianduList(String id, int count, int page, RequestParams params, ResponseCallback callback) {
        RequestMode.getRequest("http://gank.io/api/xiandu/data/id/" + id + "/count/" + count + "/page/" + page, params, callback, XianduListBean.class);
    }
}
