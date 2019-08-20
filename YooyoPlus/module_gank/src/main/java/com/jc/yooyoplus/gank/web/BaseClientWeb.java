package com.jc.yooyoplus.gank.web;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by jackie on 18/2/8.
 */

public class BaseClientWeb extends WebViewClient {

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler,
                                   SslError error) {
        // TODO Auto-generated method stub
        super.onReceivedSslError(view, handler, error);
        handler.proceed();
    }

    @Override
    public void onReceivedError(WebView view, int errorCode,
                                String description, String failingUrl) {
        // TODO Auto-generated method stub
//		super.onReceivedError(view, errorCode, description, failingUrl);
        switch (errorCode) {
            case -1://一般错误

                break;
            case -2://服务器或代理主机名查找失败

                break;
            case -3://不支持的认证方案（不是基本或摘要）

                break;
            case -4://用户身份验证失败

                break;
            case -5://使用代理用户身份验证失败

                break;
            case -6://无法连接到服务器

                break;
            case -7://无法读取或写入到服务器

                break;
            case -8://链接超时

                break;
            case -9://过多的重定向

                break;
            case -10://不支持的URI方案

                break;
            case -11://无法执行SSL握手

                break;
            case -12://错误的URL

                break;
            case -13://通用文件错误

                break;
            case -14://文件未找到

                break;
            case -15://请求超负荷、过载

                break;

            default:
                break;
        }
    }
}
