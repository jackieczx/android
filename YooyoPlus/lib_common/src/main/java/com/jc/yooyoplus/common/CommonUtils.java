package com.jc.yooyoplus.common;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class CommonUtils {

    public static float scaledDensity;
    public static float densityDpi;
    public static float density;
    /**当前手机屏幕的宽度*/
    public static int screenWidth_ = 0;
    /**当前手机屏幕的高度*/
    public static int screenHeight_ = 0;
    /**基准屏幕宽*/
    final static float origWidth_ = 720;
    /**基准屏幕高*/
    final static float origHeight_ = 1280;
    /**缩放系数*/
    public static float DM_DENSITY;
    /**宽度缩放比例*/
    public static float SCALEDATE_W;
    /**高度缩放比例*/
    public static float SCALEDATE_H;
    /**
     * 状态栏高度
     * */
//    public static int statusBarHeight = 0;

    /**
     * 获取屏幕参数
     * 2015-4-30下午5:46:18
     * @param activity
     */
    public static void calculateScreenSize(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        scaledDensity = dm.scaledDensity;
        density = dm.density;
        densityDpi = dm.densityDpi;
        screenWidth_ = dm.widthPixels;
        screenHeight_ = dm.heightPixels;
        float scl = origWidth_/dm.densityDpi;
        setScaledParams(dm.scaledDensity);
//        setScaledParams(scl);
        //获取status_bar_height资源的ID
//        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
//        if (resourceId > 0) {
//            //根据资源ID获取响应的尺寸值
//            statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);
//        }
    }

    /**
     * 计算宽高
     * @param screenWidth 理想的屏幕宽度
     * @param senseW 理想的view宽度
     * @param scaleW 宽比重
     * @param scaleH 高比重
     * @return
     */
    public static int[] getSenseWH(int screenWidth, int senseW, int scaleW, int scaleH){
        int[] wh = new int[2];
        wh[0] = (int) (((float)(CommonUtils.screenWidth_ * senseW)) / screenWidth);
        wh[1] = (int) (((float)(wh[0] * scaleH)) / scaleW);
        return wh;
    }

    /**
     * 获取缩放参数
     * 2015-4-30下午5:46:06
     * @param density
     * @param density
     */
    private static void setScaledParams(float density) {
        // TODO Auto-generated method stub
        DM_DENSITY = density/2;//2是720的密度比
        SCALEDATE_W = screenWidth_ / origWidth_;
        SCALEDATE_H = SCALEDATE_W;
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 拼接HTML文本
     * @param strings
     * @return
     */
    public static String makeHtml(String...strings){
        if (strings == null || strings.equals("") || strings.length <= 0) {
            return "";
        }

        StringBuffer bf = new StringBuffer();
        bf.append("<!DOCTYPE HTML>");
        bf.append("<html>");
        bf.append("<head>");
        bf.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
        bf.append("<meta content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0,minimum-scale=1.0, user-scalable=no\" name=\"viewport\"/>");
        bf.append("</head>");
        bf.append("<body>");
        bf.append("<head>");
        for (String s:strings) {
            if (s == null) {
                continue;
            }
            //特殊处理富文本中有图片，没有width属性，图片超宽的问题
//			int i1 = s.indexOf("width=");
            int i2 = s.indexOf("<img");
            if (i2 != -1 ) {
                s = s.replaceAll("<img", "<img width=\"100%\"");
                if(s.indexOf("src=\"//") != -1){
                    s = s.replaceAll("src=\"//", "src=\"http://");
                }
            }
            bf.append(" ");
            bf.append(s.trim());
        }
        bf.append("</body>");
        bf.append("</html>");
        return bf.toString();
    }
}
