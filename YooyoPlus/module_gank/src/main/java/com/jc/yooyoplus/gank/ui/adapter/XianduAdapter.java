package com.jc.yooyoplus.gank.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jc.yooyoplus.common.view.recyclerview.ViewHolder;
import com.jc.yooyoplus.common.view.recyclerview.adapter.CommonAdapter;
import com.jc.yooyoplus.gank.R;
import com.jc.yooyoplus.gank.mvp.model.bean.XianduListBean;
import com.jc.yooyoplus.gank.web.WebActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class XianduAdapter extends CommonAdapter<XianduListBean.ResultsBean> {

    private Context mContext;
    private List<XianduListBean.ResultsBean> mData;
    private int mLayoutId;

    public XianduAdapter(Context context, List<XianduListBean.ResultsBean> data, int layoutId) {
        super(context, data, layoutId);
        this.mContext = context;
        this.mData = data;
        this.mLayoutId = layoutId;
    }

    @Override
    protected void bindData(ViewHolder holder, final XianduListBean.ResultsBean data, int position) {

        holder.setText(R.id.gank_item_xiandu_title, data.getTitle());

        SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
        String published = data.getPublished_at().replace("Z", " UTC");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        try {
            Date callbackTimeStart = format.parse(published);
            holder.setText(R.id.gank_item_xiandu_published_at, sdf.format(callbackTimeStart));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(data.getUrl() != null) {
                    Intent intent = new Intent(mContext, WebActivity.class);
                    intent.putExtra("title", data.getTitle());
                    intent.putExtra("url", data.getUrl());
                    mContext.startActivity(intent);
                }

            }
        });
        holder.setOnItemLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }

    public void addItemData(List<XianduListBean.ResultsBean> list) {
        this.mData.addAll(list);
        notifyDataSetChanged();
    }

    public void clearItemList() {
        this.mData.clear();
    }
}
