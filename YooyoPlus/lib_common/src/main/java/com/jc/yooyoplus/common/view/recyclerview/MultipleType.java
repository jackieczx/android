package com.jc.yooyoplus.common.view.recyclerview;

/**
 * 多布局条目类型
 * @param <T>
 */
public interface MultipleType<T> {
    int getLayoutId(T item, int position);
}
