package com.cl.fragments;

import com.cl.base.BaseFragment;
import com.cl.base.BaseMvpFragment;
import com.cl.frame.ICommonModule;
import com.cl.model.CurriculumModule;
import com.cl.the_projext.R;

public class CurriculumFragment extends BaseMvpFragment {

    @Override
    public ICommonModule setModel() {
        return new CurriculumModule();
    }

    @Override
    public int setLayoutId() {
        return R.layout.curriculum_fragment;
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
