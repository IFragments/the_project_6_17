package com.cl.fragments;

import com.cl.base.BaseFragment;
import com.cl.base.BaseMvpFragment;
import com.cl.frame.ICommonModule;
import com.cl.model.MaterialModule;
import com.cl.the_projext.R;

public class MaterialFragment extends BaseMvpFragment {

    @Override
    public ICommonModule setModel() {
        return new MaterialModule();
    }

    @Override
    public int setLayoutId() {
        return R.layout.material_fragment;
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
