package com.cl.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.cl.adapter.MyFragmentAdapter;
import com.cl.base.BaseFragment;
import com.cl.base.BaseMvpFragment;
import com.cl.frame.ICommonModule;
import com.cl.model.MaterialModule;
import com.cl.the_projext.R;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MaterialFragment extends BaseFragment {

    @BindView(R.id.tab_layout)
    SegmentTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private Unbinder mBind;
    private List<Fragment> mFragments = new ArrayList<>();
    private MyFragmentAdapter mFragmentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.material_fragment, container, false);
        mBind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Collections.addAll(mFragments, MaterialGroupFragment.newInstance(), RecentlyBestFragment.newInstance());
        String[] strings = {"资料小组", "最新精华"};
        mFragmentAdapter = new MyFragmentAdapter(getChildFragmentManager(), mFragments, Arrays.asList(strings));
        viewPager.setAdapter(mFragmentAdapter);
        tabLayout.setTabData(strings);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBind != null) mBind.unbind();
    }
}
