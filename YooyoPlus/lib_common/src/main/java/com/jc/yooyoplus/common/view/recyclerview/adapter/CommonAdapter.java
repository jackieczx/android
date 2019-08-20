package com.jc.yooyoplus.common.view.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jc.yooyoplus.common.view.recyclerview.MultipleType;
import com.jc.yooyoplus.common.view.recyclerview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private LayoutInflater mInflater;

    private MultipleType<T> mTypeSupport;

    private OnItemClickListener mItemClickListener;

    private OnItemLongClickListener mItemLongClickListener;

    private int mLayoutId;
    private Context mContext;
    private List<T> mData;

    public CommonAdapter(Context context, List<T> data, int layoutId) {
        super();
        this.mLayoutId = layoutId;
        this.mData = data;
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public CommonAdapter(Context context, List<T> data, MultipleType<T> typeSupport) {
        this(context, data, -1);
        this.mTypeSupport = typeSupport;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mTypeSupport != null) {
            //需要多布局
            this.mLayoutId = viewType;
        }
        //创建view
        View view = mInflater.inflate(mLayoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //绑定数据
        bindData(holder, mData.get(position), position);

        if(mItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(mData.get(position), position);
                }
            });
        }

        if(mItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mItemLongClickListener.onItemLongClick(mData.get(position), position);
                }
            });
        }
    }

    /**
     * 将必要参数传递出去
     * @param holder
     * @param data
     * @param position
     */
    protected abstract void bindData(ViewHolder holder, T data, int position);

    @Override
    public int getItemViewType(int position) {
        //多布局问题
        if(mTypeSupport != null) {
            return mTypeSupport.getLayoutId(mData.get(position), position);
        }else {
            return super.getItemViewType(position);
        }

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
