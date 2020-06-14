package com.cl.fragments;

import com.cl.base.BaseMvpFragment;
import com.cl.frame.ICommonModule;
import com.cl.the_projext.R;

public class RecentlyBestFragment extends BaseMvpFragment {

    public static RecentlyBestFragment newInstance() {
        RecentlyBestFragment fragment = new RecentlyBestFragment();
        return fragment;
    }


    @Override
    public ICommonModule setModel() {
        return null;
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_recently_best;
    }

    @Override
    public void setUpView() {

    }

    @Override
    public void setUpData() {

    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {

    }
}
