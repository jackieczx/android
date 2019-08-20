package com.jc.yooyoplus.gank.web;

import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * Created by jackie on 18/2/8.
 */

public class ChromeForMain extends BaseClientChrome {

    //	private ProgressDialog progressDialog;
//    private NumberProgressBar mProgressBar;
    private MyWebView webView_;
    private static final String html_prefix = "<!DOCTYPE html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\"/><meta name=\"viewport\" content=\"width=device-width,device-height,user-scalable=no\" /><style type=\"text/css\">html,body {margin:0;padding:0;width:100%;height:100%;} html {display: table;} body {display: table-cell;vertical-align:middle;text-align:center;}</style></head><body>";
    private static final String html_suffix = "</body></html>";
    private ProgressBar mProgressBar;

    private WebActivity activity;

    public ChromeForMain(MyWebView webView, WebActivity activity) {
        super();
        this.activity = activity;
        this.webView_ = webView;
		this.mProgressBar = webView_.mBar;
//        mProgressBar = webView.mBar;
    }

    @Override
    public void onProgressChanged(final WebView view, int progress) {

        if(progress == 100){
            mProgressBar.setVisibility(View.GONE);//加载完网页进度条消失
        }
        else{
            mProgressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
            mProgressBar.setProgress(progress);//设置进度值
        }

        /**==========顶部线性进度条=========**/
//        if (progress == 100) {
//            mProgressBar.setProgress(mProgressBar.getMax());
//            final Timer timer = new Timer();
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    ((Activity)view.getContext()).runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (mProgressBar != null) {
//                                mProgressBar.setVisibility(View.GONE);
//                            }
//                            timer.cancel();
//                        }
//                    });
//                }
//            }, 400, 1000);
//
//        }else {
//            if (View.GONE == mProgressBar.getVisibility()) {
//                mProgressBar.setVisibility(View.VISIBLE);
//            }
//            mProgressBar.setProgress(progress);
//        }


        /**==========对话框进度条=========**/
//		if (progress < 100) {
//			progressDialog.setProgress(progress);
//			if (!progressDialog.isShowing()) {
//				progressDialog.show();
//			}
//		}
//		if (progress == 100 && progressDialog.isShowing()) {
//			progressDialog.setProgress(progress);
////			progressDialog.dismiss();
//			progressDialog.hide();
//		}
        super.onProgressChanged(view, progress);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        activity.setTitle(title);

        if (title.equalsIgnoreCase("404 Not Found")) {
            webView_.stopLoading();
            webView_.loadUrl(MyWebView.ERR_URL/*"data:text/html;charset=UTF-8,"
					+ android.net.Uri.encode(html_prefix + "<p>"
							+ "页面丢失了。"
							+ "</p>" + html_suffix)*/);
        }
    }
}
