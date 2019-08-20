package com.jc.yooyoplus.gank.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * Created by jackie on 18/2/8.
 */

public class MyWebView extends WebView {

    public WebActivity activity_;
    //	public ProgressDialog progressDialog;
//    public NumberProgressBar mBar;
    public ProgressBar mBar;
    /** js回调函数名 */
    public String callback_ = "";
    /**选择图片 */
    public final static byte REQ_IMG = 0x01;

    /**选择相机*/
    public final static byte REQ_CAMERA = 0x03;

    public static volatile String errUrl = "";

    public static final String ERR_URL = "file:///android_asset/neterror/error.html";
    public static final String HETONG_URL = "file:///android_asset/neterror/contract.html";
    public static final String ORDER_CONVENTION_URL = "file:///android_asset/neterror/order_convention.html";
    public static final String ORDER_CONTRACT_URL = "file:///android_asset/neterror/order_contract.html";
    //会员章程，regulations
    public static final String ORDER_VIP_REGULATIONS_URL = "file:///android_asset/neterror/order_vip_regulations.html";

    public String[] patternUrl = new String[]{};
    public static final byte HOME = 0x20;
    public static final byte DISCOVER = 0x30;
    public static final byte VIP = 0x40;
    public static final byte USER= 0x50;
    /**弹出选择框*/
    public static final byte CHO = 0x10;
    /**记录由activity 跳转到WAP页面的第一个URL*/
    public String FIRST_RUL = "";

    /** 原生返回时需要跳转的页面 */
    public String backUrl;

    public MyWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public MyWebView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }


    public void init(WebActivity activity, String firstUrl, ProgressBar bar){//NumberProgressBar bar
        this.activity_ = activity;
        this.FIRST_RUL = firstUrl;
        this.mBar = bar;
        settingWeb();
    }
    /**
     * 设置webview
     * lvke
     * 2015-7-22上午9:46:49
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void settingWeb(){
//        patternUrl = activity_.getResources().getStringArray(R.array.pattern_url);
        setBackgroundColor(0);
        // getBackground().setAlpha(0);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        getSettings().setJavaScriptEnabled(true);
        String userAgent = getSettings().getUserAgentString();
        int indexOfBlank = 0;
//        if(userAgent != null &&  (indexOfBlank = userAgent.indexOf(" ")) != -1){
//            userAgent = "YooYo/".concat(PhoneUtils.getAppVersionNo(getContext()).toString()) + userAgent.substring(indexOfBlank);
//        }
//        LogUtils.e("webview userAgent : "+userAgent);
        getSettings().setUserAgentString(userAgent);
        // 打开本地缓存提供JS调用
        getSettings().setDomStorageEnabled(true);
        //根据cache-control决定是否从网络上取数据
//		getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);  
        //只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据.只能缓存浏览过的页面
//		getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); 
        //不使用网络，只读取本地缓存数据.如果第一次打不开页面，当有网络的时候依然显示的是缓存的“找不到网页”
//      getSettings().setCacheMode(WebSettings.LOAD_CACHE_ONLY); 
        //不使用缓存，只从网络获取数据.
//		getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		
		/*
		 * String appCachePath =
		 * getApplicationContext().getCacheDir().getAbsolutePath();
		 * getSettings().setAppCachePath(appCachePath);
		 * getSettings().setAllowFileAccess(true);
		 * getSettings().setAppCacheEnabled(true);
		 */
        removeJavascriptInterface("searchBoxJavaBridge_");
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
//		 addJavascriptInterface(new WebAppInterface(this), "Android");

        // setWebChromeClient(new WebChromeFilterMy());
        setWebChromeClient(new ChromeForMain(this, activity_));
        setWebViewClient(new ClientForMain(this,activity_));
        clearHistory();
    }

//	private void createDlg(){
//		progressDialog = new ProgressDialog(activity_);
//		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//		progressDialog.setMessage(activity_.getString(R.string.loading));
//		progressDialog.setCanceledOnTouchOutside(false);
//		progressDialog.setCancelable(true);
//		progressDialog.setMax(100);
//		progressDialog.setOnCancelListener(new OnCancelListener() {
//			@Override
//			public void onCancel(DialogInterface dialog) {
//				stopLoading();
//				MToast.show(activity_, activity_.getString(R.string.load_canceled));
//			}
//		});
//	}

    /**
     * 打开系统相册
     * lvke
     * 2015-7-22上午10:39:55
     */
//    private void chooseDialog() {
//        CharSequence[] items = { activity_.getString(R.string.album),
//                activity_.getString(R.string.camera) };
//        AlertDialog dialog = new AlertDialog.Builder(activity_)
//                .setTitle(activity_.getString(R.string.choose_from))
//                .setItems(items, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch (which) {
//                            case 0: {
//                                Intent intent = new Intent(Intent.ACTION_PICK,
//                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                                activity_.startActivityForResult(intent, REQ_IMG);
//                            }
//                            break;
//
//                            case 1: {
//                                Intent intent = new Intent(
//                                        MediaStore.ACTION_IMAGE_CAPTURE);
//                                activity_.startActivityForResult(intent, REQ_CAMERA);
//                            }
//                            break;
//                        }
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                }).create();
//        dialog.show();
//    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case CHO:
//                    chooseDialog();
                    break;
                case 2:
                    break;
                default:
                    break;
            }
        };

    };
    /**
     * 过滤webview返回事件
     * lvke
     * 2015-8-28下午7:10:48
     */
    public void back() {
        String url = getUrl();
        if(ERR_URL.equals(url)){
            activity_.finish();
            return;
        }
        if (url != null) {
            if (FIRST_RUL != null && url.equalsIgnoreCase(FIRST_RUL)) {
                //已经返回到当初进来的第一个WAP页面。关闭当前的activity
                activity_.finish();
            }else if (url.startsWith("data:")) {//页面请求失败，报错
                if (canGoBackOrForward(-2)) {
                    goBackOrForward(-2);
					/*activity_.finish();
				} else if (canGoBack()) {
					goBack();*/
                }else {
                    activity_.finish();
                }
            } else if (canGoBack()) {
                goBack();
            }else {
                activity_.finish();
            }
        }else if(canGoBack()){
            goBack();
        }else {
            activity_.finish();
        }
    };

    @Override
    public void goBack() {
        // TODO Auto-generated method stub
        super.goBack();
        if(backUrl != null && backUrl.length() > 0){
            loadUrl(backUrl);
        }
    }

    public void setBackUrl(String backUrl) {
        // TODO Auto-generated method stub
        this.backUrl = backUrl;
    }

}
