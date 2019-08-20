package com.jc.yooyoplus.gank.mvp.model.bean;

import android.os.Parcel;

import com.jc.yooyoplus.gank.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

public class CategoriesChildBean extends BaseBean {


    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean implements android.os.Parcelable {
        /**
         * _id : 57c83792421aa97cada9b79d
         * created_at : 2016-09-01T22:13:38.420Z
         * icon : http://ww2.sinaimg.cn/large/610dc034gw1f9sg2pq9ufj202s02s0sj.jpg
         * id : qdaily
         * title : 好奇心日报
         */

        private String _id;
        private String created_at;
        private String icon;
        private String id;
        private String title;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this._id);
            dest.writeString(this.created_at);
            dest.writeString(this.icon);
            dest.writeString(this.id);
            dest.writeString(this.title);
        }

        public ResultsBean() {
        }

        protected ResultsBean(Parcel in) {
            this._id = in.readString();
            this.created_at = in.readString();
            this.icon = in.readString();
            this.id = in.readString();
            this.title = in.readString();
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

    public CategoriesChildBean() {
    }

    protected CategoriesChildBean(Parcel in) {
        super(in);
        this.results = new ArrayList<ResultsBean>();
        in.readList(this.results, ResultsBean.class.getClassLoader());
    }

    public static final Creator<CategoriesChildBean> CREATOR = new Creator<CategoriesChildBean>() {
        @Override
        public CategoriesChildBean createFromParcel(Parcel source) {
            return new CategoriesChildBean(source);
        }

        @Override
        public CategoriesChildBean[] newArray(int size) {
            return new CategoriesChildBean[size];
        }
    };
}
