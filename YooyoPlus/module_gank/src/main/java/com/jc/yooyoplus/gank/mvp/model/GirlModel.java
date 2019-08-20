package com.jc.yooyoplus.gank.mvp.model;

import com.jc.yooyoplus.gank.mvp.model.bean.GirlListBean;
import com.jc.yooyoplus.gank.net.RequestMode;
import com.jc.yooyoplus.gank.net.RequestParams;
import com.jc.yooyoplus.gank.net.ResponseCallback;

public class GirlModel {

    //获取女孩数据列表
    public void getGirlList(int count, int page, RequestParams params, ResponseCallback callback) {
        RequestMode.getRequest("http://gank.io/api/data/福利/" + count + "/" + page, params, callback, GirlListBean.class);
    }
}
