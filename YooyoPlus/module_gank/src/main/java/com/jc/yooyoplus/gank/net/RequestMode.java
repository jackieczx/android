package com.jc.yooyoplus.gank.net;

import java.io.File;
import java.util.List;

/**
 * 请求模式
 */
public class RequestMode {

    /**
     * GET请求
     * @param url URL请求地址
     * @param params 入参
     * @param callback 回调接口
     * @param clazz 需要解析的实体类
     */
    public static void getRequest(String url, RequestParams params,
                                  ResponseCallback callback, Class<?> clazz) {
        MyOkHttpClient.get(MyRequest.createGetRequest(url, params),
                new ResposeDataHandle(callback, clazz));
    }

    /**
     * POST请求
     * @param url URL请求地址
     * @param params 入参
     * @param callback 回调接口
     * @param clazz 需要解析的实体类
     */
    public static void postRequest(String url, RequestParams params,
                                   ResponseCallback callback, Class<?> clazz) {
        MyOkHttpClient.post(MyRequest.createPostRequest(url, params),
                new ResposeDataHandle(callback, clazz));
    }

    /**
     * 下载图片 Get方式
     */
    public static void getLoadImg(String url,RequestParams params,String imgPath, ResponseByteCallback callback){
        MyOkHttpClient.downLadImg(MyRequest.createGetRequest(url, params),imgPath,callback);
    }

    /**
     * 下载图片 Post方式
     */
    public static void postLoadImg(String url,RequestParams params,String imgPath, ResponseByteCallback callback){
        MyOkHttpClient.downLadImg(MyRequest.createPostRequest(url, params),imgPath,callback);
    }

    /**
     * 表单和媒体 图文混合
     */
    public static void postMultipart(String url, RequestParams params,
                                     List<File> files, ResponseCallback callback, Class<?> clazz) {
        MyOkHttpClient.post(MyRequest.createMultipartRequest(url, params, files),
                new ResposeDataHandle(callback, clazz));
    }
}
