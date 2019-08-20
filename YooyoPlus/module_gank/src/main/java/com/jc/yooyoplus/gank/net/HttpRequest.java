package com.jc.yooyoplus.gank.net;

/**
 * 接口
 */
public class HttpRequest {

    /**
     * 获取闲读的分类
     * @param params
     * @param callback
     */
    public static void getXuanduCategories(RequestParams params, ResponseCallback callback) {
        RequestMode.getRequest("http://gank.io/api/xiandu/categories", params, callback, String.class);
    }
}
