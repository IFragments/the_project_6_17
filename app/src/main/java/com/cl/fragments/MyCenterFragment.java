package com.cl.fragments;

import com.cl.base.BaseFragment;
import com.cl.base.BaseMvpFragment;
import com.cl.frame.ICommonModule;
import com.cl.the_projext.R;

public class MyCenterFragment extends BaseMvpFragment {

    @Override
    public ICommonModule setModel() {
        return null;
    }

    @Override
    public int setLayoutId() {
        return R.layout.my_center_fragment;
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
