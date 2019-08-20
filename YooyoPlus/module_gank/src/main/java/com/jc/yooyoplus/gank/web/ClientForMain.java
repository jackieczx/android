package com.jc.yooyoplus.gank.web;

import android.graphics.Bitmap;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.ByteArrayInputStream;

/**
 * Created by jackie on 18/2/8.
 */

public class ClientForMain extends BaseClientWeb {
    private WebActivity activity_;
    private MyWebView webView_;
    /**因错，上次访问的url*/
    private String lastUrl_ = "";

    private WebResourceResponse resource;

    public ClientForMain(MyWebView webView, WebActivity activity) {
        super();
        this.webView_ = webView;
        this.activity_ = activity;

        byte[] data = new byte[0];
        resource = new WebResourceResponse("text/javascript", "utf-8",
                new ByteArrayInputStream(data));

    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
//		Uri uri = Uri.parse(url);
//		if (uri.getScheme().equalsIgnoreCase("http")
//				|| uri.getScheme().equalsIgnoreCase("https")) {
//		}
    }

    @Override
    public void onReceivedError(WebView view, int errorCode,
                                String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        //只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据.只能缓存浏览过的页面
        webView_.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        if (lastUrl_ != null && lastUrl_.equalsIgnoreCase(failingUrl)) {//两次请求都无法显示网页
//        	webView_.loadUrl("data:text/html;charset=UTF-8,"
//    				+ android.net.Uri.encode(html_prefix + "<p>"
//    						+ description + "</p>" + html_suffix));
//    		MToast.show(activity_, description);
//        }else {
//        	lastUrl_ = failingUrl;
//        	webView_.loadUrl(failingUrl);
//        }

//		Intent intent = new Intent(activity_, WebViewActivity.class);
//		intent.putExtra("url", "file:///android_asset/neterror/error.html");
//		activity_.startActivityForResult(intent, WebViewMy.REQ_ERROR);
//		webView_.stopLoading();

        webView_.errUrl = failingUrl;

        webView_.loadUrl(MyWebView.ERR_URL);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(final WebView view, String url) {
        WebResourceResponse res = null;
        try {
            res = super.shouldInterceptRequest(view, url);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return resource;
        }
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(url.startsWith("http")){
            view.loadUrl(url);
        }
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if(url.equals(webView_.backUrl)){
            webView_.backUrl = "";
        }
    }

    /**
     * 判断是不是图片url
     * lvke
     * 2015-5-13下午3:18:54
     * @param url
     * @return
     */
    public boolean isImgUrl(String url){
        if (url == null || url.equalsIgnoreCase("")) {
            return false;
        }
        if (url.endsWith(".jpg") || url.endsWith(".png")) {
            return true;
        }
        return false;
    }

}
