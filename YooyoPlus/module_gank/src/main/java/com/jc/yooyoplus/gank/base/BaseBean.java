package com.jc.yooyoplus.gank.base;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

public class BaseBean implements Parcelable {

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    private boolean error;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.error ? (byte) 1 : (byte) 0);
    }

    public BaseBean() {
    }

    protected BaseBean(Parcel in) {
        this.error = in.readByte() != 0;
    }

}
