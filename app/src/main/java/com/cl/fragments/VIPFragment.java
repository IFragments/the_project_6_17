package com.cl.fragments;

import com.cl.base.BaseFragment;
import com.cl.base.BaseMvpFragment;
import com.cl.frame.ICommonModule;
import com.cl.model.VipModule;
import com.cl.the_projext.R;

public class VIPFragment extends BaseMvpFragment {

    @Override
    public ICommonModule setModel() {
        return new VipModule();
    }

    @Override
    public int setLayoutId() {
        return R.layout.vip_fragment;
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
