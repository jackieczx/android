package com.jc.yooyoplus.gank.ui.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.jc.yooyoplus.common.CommonUtils;
import com.jc.yooyoplus.common.view.recyclerview.MultipleType;
import com.jc.yooyoplus.common.view.recyclerview.ViewHolder;
import com.jc.yooyoplus.common.view.recyclerview.adapter.CommonAdapter;
import com.jc.yooyoplus.gank.R;
import com.jc.yooyoplus.gank.mvp.model.bean.GirlListBean;

import java.util.List;

public class GirlAdapter extends CommonAdapter<GirlListBean.ResultsBean> {

    private Context mContext;
    private List<GirlListBean.ResultsBean> mData;
    private int mLayoutId;
    private int imgWidth_dp;
    private int imgHeight_dp;

    private MyDialog myDialog;
    private ImageView bigImageView;

    RequestOptions options = new RequestOptions()
        .centerCrop()
        .placeholder(R.mipmap.bg)
        .error(R.mipmap.bg)
        .priority(Priority.HIGH);

    public GirlAdapter(Context context, List<GirlListBean.ResultsBean> data, int layoutId) {
        super(context, data, layoutId);
        this.mContext = context;
        this.mData = data;
        this.mLayoutId = layoutId;

        //1024 * 1365
        int wh[] = CommonUtils.getSenseWH(CommonUtils.screenWidth_,
                CommonUtils.screenWidth_ - CommonUtils.dip2px(context, 12), 1024, 1365);
        imgWidth_dp = wh[0];
        imgHeight_dp = wh[1];

        View view = LayoutInflater.from(context).inflate(R.layout.gank_dialog_girl, null);
        myDialog = new MyDialog(context, view, R.style.DialogTheme);
        bigImageView = (ImageView) view.findViewById(R.id.gank_dialog_girl_iv);
        bigImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

    }

    public GirlAdapter(Context context, List<GirlListBean.ResultsBean> data, MultipleType<GirlListBean.ResultsBean> typeSupport) {
        super(context, data, typeSupport);
    }

    @Override
    protected void bindData(ViewHolder holder, GirlListBean.ResultsBean data, int position) {

        ImageView imageView = holder.getView(R.id.gank_item_girl_iv);
        final String url = data.getUrl();
        if(url == null) {
            Glide.with(mContext).clear(imageView);
            imageView.setImageDrawable(null);
            imageView.setTag(R.id.image_tag, position);
            return;
        }
        Object tag = imageView.getTag(R.id.image_tag);
        if(tag != null && (int)tag != position) {
            //如果tag不是Null,并且同时tag不等于当前的position。
            //说明当前的viewHolder是复用来的
            Glide.with(mContext).clear(imageView);
        }
//        Glide.with(mContext).load(url).apply(options).into(imageView);
        Glide.with(mContext).load(url).override(imgWidth_dp, imgHeight_dp).apply(options).into(imageView);
        //给ImageView设置唯一标记。
        imageView.setTag(R.id.image_tag, position);

        holder.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(mContext).clear(bigImageView);
                Glide.with(mContext).load(url).override(imgWidth_dp, imgHeight_dp).apply(options).into(bigImageView);
                myDialog.show();
            }
        });
     }

    public void addItemData(List<GirlListBean.ResultsBean> list) {
        this.mData.addAll(list);
        notifyDataSetChanged();
    }

    public void clearItemList() {
        this.mData.clear();
    }

    class MyDialog extends Dialog {

        public MyDialog(Context context, View layout, int themeResId) {
            super(context, themeResId);
            setContentView(layout);
            Window window = getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.CENTER;
            window.setAttributes(params);
        }
    }
}
