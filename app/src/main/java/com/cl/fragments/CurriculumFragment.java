package com.cl.fragments;

import androidx.fragment.app.Fragment;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;
import androidx.viewpager.widget.ViewPager;

import com.cl.adapter.MyFragmentAdapter;
import com.cl.adapter.ViewPagerScrollAdapter;
import com.cl.base.BaseMvpFragment;
import com.cl.frame.ICommonModule;
import com.cl.model.CurriculumModule;
import com.cl.the_projext.R;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

public class CurriculumFragment extends BaseMvpFragment {

    @BindView(R.id.slide_layout)
    SlidingTabLayout slidingTabLayout;
    @BindView(R.id.home_curr_viewPager)
    ViewPager homeCurrViewPager;

    private List<String> titleList = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private MyFragmentAdapter mFragmentAdapter;

    public static final int TRAINTAB = 3;
    public static final int BESTTAB = 1;
    public static final int PUBLICTAB = 2;

    @Override
    public CurriculumModule setModel() {
        return null;
    }

    @Override
    public int setLayoutId() {
        return R.layout.curriculum_fragment;
    }

    @Override
    public void setUpView() {
        Collections.addAll(titleList, "训练营", "精品课", "公开课");
        Collections.addAll(mFragments, CurriculumChildFragment.getInstance(TRAINTAB), CurriculumChildFragment.getInstance(BESTTAB), CurriculumChildFragment.getInstance(PUBLICTAB));
        mFragmentAdapter = new MyFragmentAdapter(getChildFragmentManager(), mFragments, titleList);
        homeCurrViewPager.setAdapter(mFragmentAdapter);
        slidingTabLayout.setViewPager(homeCurrViewPager);

    }

    @Override
    public void setUpData() {

        mFragmentAdapter.notifyDataSetChanged();
        slidingTabLayout.notifyDataSetChanged();
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {

    }
}
