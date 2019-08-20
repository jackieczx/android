package com.jc.yooyoplus.gank.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jc.yooyoplus.gank.R;
import com.jc.yooyoplus.gank.base.BaseFragment;
import com.jc.yooyoplus.gank.base.BaseFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/gank/news")
public class NewsFragment extends BaseFragment {

    /**
     * 存放 tab 标题
     */
    private List<String> mTabTitleList = new ArrayList<String>();

    private List<Fragment> mFragmentList = new ArrayList<Fragment>();

    private TabLayout tabLayout;

    private ViewPager viewPager;

    private String[][] titleArr = {{"zhihu","知乎日报"},{"solidot","Solidot"},{"williamlong","月光博客"},{"engadget","Engadget"},{"ifanr","爱范儿"},{"techcrunch","TechCrunch"}};


    @Override
    protected int getLayoutId() {
        return R.layout.gank_fragment_news;
    }

    @Override
    protected void initView(View rootView) {
        for(String[] title : titleArr) {
            mTabTitleList.add(title[1]);
            XianduFragment fragment = XianduFragment.getInstance(title[0]);
            mFragmentList.add(fragment);
        }

        viewPager = rootView.findViewById(R.id.gank_activity_news_vp);
        viewPager.setAdapter(new BaseFragmentAdapter(getChildFragmentManager(), mFragmentList, mTabTitleList));

        tabLayout = rootView.findViewById(R.id.gank_activity_news_tb);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void lazyLoad() {

    }
}
