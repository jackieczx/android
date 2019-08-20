package com.jc.yooyoplus.gank.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.jc.yooyoplus.gank.R;
import com.jc.yooyoplus.gank.base.BaseActivity;
import com.jc.yooyoplus.gank.base.BaseFragmentAdapter;
import com.jc.yooyoplus.gank.mvp.contract.XianduContract;
import com.jc.yooyoplus.gank.mvp.model.bean.CategoriesBean;
import com.jc.yooyoplus.gank.mvp.model.bean.XianduListBean;
import com.jc.yooyoplus.gank.mvp.presenter.XianduPresenter;
import com.jc.yooyoplus.gank.ui.fragment.XianduFragment;

import java.util.ArrayList;
import java.util.List;

public class XianduActivity extends BaseActivity {

    /**
     * 存放 tab 标题
     */
    private List<String> mTabTitleList = new ArrayList<String>();

    private List<Fragment> mFragmentList = new ArrayList<Fragment>();

    private TabLayout tabLayout;

    private ViewPager viewPager;

    private String[][] titleArr = {{"zhihu","知乎日报"},{"solidot","Solidot"},{"williamlong","月光博客"},{"engadget","Engadget"},{"ifanr","爱范儿"},{"techcrunch","TechCrunch"}};

    @Override
    protected int layoutId() {
        return R.layout.gank_activity_xiandu;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        for(String[] title : titleArr) {
            mTabTitleList.add(title[1]);
            XianduFragment fragment = XianduFragment.getInstance(title[0]);
            mFragmentList.add(fragment);
        }

        viewPager = findViewById(R.id.gank_activity_xiandu_vp);
        viewPager.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(), mFragmentList, mTabTitleList));

        tabLayout = findViewById(R.id.gank_activity_xiandu_tb);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    protected void startRequest() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
