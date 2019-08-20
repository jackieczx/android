package com.jc.yooyoplus.common.view.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jc.yooyoplus.common.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mView;



    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = new SparseArray<>();
    }

    public <T extends View> T getView(int viewId)  {
        //对已有的view做缓存
        View view = mView.get(viewId);
        //使用缓存的方式减少findViewById的次数
        if(view == null) {
            view = itemView.findViewById(viewId);
            mView.put(viewId, view);
        }
        return (T)view;
    }

    protected <T extends ViewGroup> T getViewGroup(int viewId) {
        //对已有的view做缓存
        View view = mView.get(viewId);
        //使用缓存的方式减少findViewById的次数
        if(view == null) {
            view = itemView.findViewById(viewId);
            mView.put(viewId, view);
        }
        return (T)view;
    }

    //通用的功能进行封装  设置文本 设置条目点击事件  设置图片
    public ViewHolder setText(int viewId, CharSequence text) {
        TextView textView = getView(viewId);
        textView.setText("" + text);
        //希望可以链式调用
        return this;
    }

    public ViewHolder setImage(Context context, int viewId, String url) {
        ImageView imageView = getView(viewId);
        if(url == null) {
            Glide.with(context).clear(imageView);
        }else {
            Glide.with(context).load(url).into(imageView);
        }

        return this;
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        itemView.setOnClickListener(onItemClickListener);
    }

    public void setOnItemLongClickListener(View.OnLongClickListener onItemLongClickListener) {
        itemView.setOnLongClickListener(onItemLongClickListener);
    }
}
