package com.jc.yooyoplus.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jc.yooyoplus.common.provider.IGankProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Autowired(name = "/gank/main")
    IGankProvider mGankProvider;

    private RadioGroup mRadioGroup;
    private RadioButton firstRadioButton;
    private RadioButton secondRadioButton;
    private RadioButton ThirdRadioButton;

    private Fragment firstFragment, secondFragment, thirdFragment;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activity_main);
//        btn = findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ARouter.getInstance().build("/yooyo/main").navigation();
//            }
//        });


        mRadioGroup = (RadioGroup) findViewById(R.id.activity_main_rg);
        firstRadioButton = (RadioButton) findViewById(R.id.activity_main_rb1);
        secondRadioButton = (RadioButton) findViewById(R.id.activity_main_rb2);
        ThirdRadioButton = (RadioButton) findViewById(R.id.activity_main_rb3);

//        if(mGankProvider != null) {
//            firstFragment = mGankProvider.getTestFragment();
//            secondFragment = mGankProvider.getGirlFragment();
//        }

        Fragment firstFragment = (Fragment) ARouter.getInstance().build("/gank/news").navigation();
        Fragment secondFragment = (Fragment) ARouter.getInstance().build("/gank/girl").navigation();
        Fragment thirdFragment = (Fragment) ARouter.getInstance().build("/gank/about").navigation();

        mFragmentList.add(firstFragment);
        mFragmentList.add(secondFragment);
        mFragmentList.add(thirdFragment);

        firstRadioButton.setChecked(true);
        activeFragment(0);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.activity_main_rb1) {
                    activeFragment(0);
                }else if(checkedId == R.id.activity_main_rb2) {
                    activeFragment(1);
                }else if(checkedId == R.id.activity_main_rb3) {
                    activeFragment(2);
                }
            }
        });


    }

    private void activeFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < mFragmentList.size(); i++) {
            Fragment fragment = mFragmentList.get(i);
            if (i == index) {
                if (!fragment.isAdded()) {
//                    String fragTag = null;
//                    if (fragment instanceof NewTicketsFragment) {
//                        fragTag = NewTicketsFragment.TAG;
//                    } else if (fragment instanceof BillManagementFragment) {
//                        fragTag = BillManagementFragment.TAG;
//                    } else if (fragment instanceof DriverPersonalFragment) {
//                        fragTag = DriverPersonalFragment.TAG;
//                    }
                    ft.add(R.id.activity_main_fl, fragment);
                }
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
        }
        ft.commit();
    }
}
