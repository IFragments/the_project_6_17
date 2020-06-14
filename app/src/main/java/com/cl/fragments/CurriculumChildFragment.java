package com.cl.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.cl.adapter.CurriculumChildAdapter;
import com.cl.base.BaseMvpFragment;
import com.cl.data.BaseInfo;
import com.cl.data.CourseListInfo;
import com.cl.data.SearchItemEntity;
import com.cl.data.SpecialtyChooseEntity;
import com.cl.frame.ApiConfig;
import com.cl.frame.LoadTypeConfig;
import com.cl.frame.constants.ConstantKey;
import com.cl.frame.constants.Constants;
import com.cl.frame.utils.ParamHashMap;
import com.cl.interfaces.DataListener;
import com.cl.model.CurriculumModule;
import com.cl.the_projext.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class CurriculumChildFragment extends BaseMvpFragment<CurriculumModule> implements DataListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int mIndex;
    private int page = 1;
    private List<SearchItemEntity> mList = new ArrayList<>();
    private CurriculumChildAdapter mAdapter;

    public static CurriculumChildFragment getInstance(int index) {
        CurriculumChildFragment fragment = new CurriculumChildFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("whichFragment", index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mIndex = (int) getArguments().get("whichFragment");
        }
    }

    @Override
    public CurriculumModule setModel() {
        return new CurriculumModule();
    }

    @Override
    public int setLayoutId() {
        return R.layout.refresh_list_layout;
    }

    @Override
    public void setUpView() {
        initRecyclerView(recyclerView, refreshLayout, this);
        mAdapter = new CurriculumChildAdapter(getContext());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setUpData() {
        mPresenter.getData(ApiConfig.COURSE_CHILD, LoadTypeConfig.NORMAL, mIndex, page);
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        BaseInfo<CourseListInfo> baseInfo = (BaseInfo<CourseListInfo>) pD[0];
        if (page == 1) {
            mAdapter.setReList(baseInfo.result.lists);
        } else {
            mAdapter.setList(baseInfo.result.lists);
        }
        refreshLayout.finishLoadMore();
        refreshLayout.finishRefresh();
    }

    @Override
    public void dataType(int mode) {
        if (mode == LoadTypeConfig.REFRESH)
            mPresenter.getData(ApiConfig.COURSE_CHILD, LoadTypeConfig.REFRESH, mIndex, 1);
        else {
            page++;
            mPresenter.getData(ApiConfig.COURSE_CHILD, LoadTypeConfig.MORE, mIndex, page);
        }
    }
}
