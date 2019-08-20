package com.jc.yooyoplus.gank.mvp.model.bean;

import android.os.Parcel;

import com.jc.yooyoplus.gank.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

public class CategoriesBean extends BaseBean {

    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean implements android.os.Parcelable {
        /**
         * _id : 57c83777421aa97cbd81c74d
         * en_name : wow
         * name : 科技资讯
         * rank : 1
         */

        private String _id;
        private String en_name;
        private String name;
        private int rank;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getEn_name() {
            return en_name;
        }

        public void setEn_name(String en_name) {
            this.en_name = en_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this._id);
            dest.writeString(this.en_name);
            dest.writeString(this.name);
            dest.writeInt(this.rank);
        }

        public ResultsBean() {
        }

        protected ResultsBean(Parcel in) {
            this._id = in.readString();
            this.en_name = in.readString();
            this.name = in.readString();
            this.rank = in.readInt();
        }

        public static final Creator<ResultsBean> CREATOR = new Creator<ResultsBean>() {
            @Override
            public ResultsBean createFromParcel(Parcel source) {
                return new ResultsBean(source);
            }

            @Override
            public ResultsBean[] newArray(int size) {
                return new ResultsBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeList(this.results);
    }

    public CategoriesBean() {
    }

    protected CategoriesBean(Parcel in) {
        super(in);
        this.results = new ArrayList<ResultsBean>();
        in.readList(this.results, ResultsBean.class.getClassLoader());
    }

    public static final Creator<CategoriesBean> CREATOR = new Creator<CategoriesBean>() {
        @Override
        public CategoriesBean createFromParcel(Parcel source) {
            return new CategoriesBean(source);
        }

        @Override
        public CategoriesBean[] newArray(int size) {
            return new CategoriesBean[size];
        }
    };
}
