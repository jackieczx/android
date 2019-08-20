package com.jc.yooyoplus.gank.web;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jc.yooyoplus.common.view.MyTextView;
import com.jc.yooyoplus.gank.R;

/**
 * Created by jackie on 18/2/8.
 */

public class WebActivity extends Activity {

    private String url_, title;
    public MyWebView webView;
    /**记录由activity 跳转到WAP页面的第一个URL*/
    public static String FIRST_RUL = "";
    private MyTextView closeTv;
    private TextView titleTv;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gank_activity_web);

        bar = (ProgressBar) findViewById(R.id.gank_activity_xiandu_pb);
        titleTv = (TextView) findViewById(R.id.gank_activity_web_title);
        webView = (MyWebView) findViewById(R.id.gank_activity_xiandu_mwv);
//        final NumberProgressBar progressBar = ConfigManager.PROGRESS_NUMBER;//(ProgressBar) findViewById(R.id.myProgressBar);
//        progressBar.incrementProgressBy(1);
//        progressBar.setMax(100);
        webView.init(this, FIRST_RUL, bar);//progressBar

        url_ = getIntent().getExtras().getString("url");
        title = getIntent().getExtras().getString("title");
        if(title != null) {
            titleTv.setText(title);
        }
        if(url_ == null){
            return;
        }

        FIRST_RUL = url_;
        webView.loadUrl(url_);

//        setLeftButton(new android.view.View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                webView.back();
//            }
//        });

        closeTv = (MyTextView)findViewById(R.id.gank_activity_web_close);
        closeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebActivity.this.finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    webView.back();

                    return true;
                default:
                    break;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
