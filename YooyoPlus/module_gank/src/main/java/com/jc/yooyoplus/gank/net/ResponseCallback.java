package com.jc.yooyoplus.gank.net;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 回调接口
 */
public abstract class ResponseCallback<T> {

    Type mType;

    public ResponseCallback() {

        //Type是 Java 编程语言中所有类型的公共高级接口。它们包括原始类型、参数化类型、数组类型、类型变量和基本类型。
        Type superclass = getClass().getGenericSuperclass();

        if (superclass instanceof Class) {
//      throw new RuntimeException("请传入实体类");
            mType = null;
        } else {
            //ParameterizedType参数化类型，即泛型
            ParameterizedType parameterized = (ParameterizedType) superclass;

            //getActualTypeArguments获取参数化类型的数组，泛型可能有多个
            //将Java 中的Type实现,转化为自己内部的数据实现,得到gson解析需要的泛型
            mType = $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
        }

    }

    //请求成功回调事件处理
    public abstract void onSuccess(T t);

    //请求失败回调事件处理
    public abstract void onFailure(OkHttpException failuer);
}
