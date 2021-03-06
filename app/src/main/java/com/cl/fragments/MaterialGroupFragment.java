package com.cl.fragments;

import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.cl.adapter.MaterialGroupAdapter;
import com.cl.base.BaseMvpFragment;
import com.cl.data.BaseInfo;
import com.cl.data.MaterialGroupListEntity;
import com.cl.frame.ApiConfig;
import com.cl.frame.FrameApplication;
import com.cl.frame.LoadTypeConfig;
import com.cl.interfaces.DataListener;
import com.cl.interfaces.OnRecyclerItemClick;
import com.cl.model.MaterialModule;
import com.cl.the_projext.LoginActivity;
import com.cl.the_projext.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.cl.adapter.MaterialGroupAdapter.FOCUS_TYPE;
import static com.cl.adapter.MaterialGroupAdapter.ITEM_TYPE;
import static com.cl.constants.JumpConstant.DATAGROUPFRAGMENT_TO_LOGIN;
import static com.cl.constants.JumpConstant.JUMP_KEY;


public class MaterialGroupFragment extends BaseMvpFragment<MaterialModule> implements DataListener, OnRecyclerItemClick {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int page = 1;
    private MaterialGroupAdapter mAdapter;
    private List<MaterialGroupListEntity> mList;

    public static MaterialGroupFragment newInstance() {
        MaterialGroupFragment fragment = new MaterialGroupFragment();
        return fragment;
    }

    @Override
    public MaterialModule setModel() {
        return new MaterialModule();
    }

    @Override
    public int setLayoutId() {
        return R.layout.refresh_list_layout;
    }

    @Override
    public void setUpView() {
        initRecyclerView(recyclerView, refreshLayout, this);
        mAdapter = new MaterialGroupAdapter(getContext());
        recyclerView.setAdapter(mAdapter);
        mList = mAdapter.getList();
        mAdapter.setOnRecyclerItemClick(this);
    }

    @Override
    public void setUpData() {
        mPresenter.getData(ApiConfig.DATA_GROUP, LoadTypeConfig.NORMAL, page);
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi) {
            case ApiConfig.DATA_GROUP:
                BaseInfo<List<MaterialGroupListEntity>> info = (BaseInfo<List<MaterialGroupListEntity>>) pD[0];
                if (page == 1) {
                    mAdapter.setReList(info.result);
                    refreshLayout.finishRefresh();
                } else {
                    mAdapter.setList(info.result);
                    refreshLayout.finishLoadMore();
                }
                break;
            case ApiConfig.CLICK_CANCEL_FOCUS:
                BaseInfo base = (BaseInfo) pD[0];
                if (base.isSuccess()) {
                    showToast("取消成功");
                    mList.get(s).setIs_ftop(0);
                    mAdapter.notifyItemChanged(s);
                }
                break;
            case ApiConfig.CLICK_TO_FOCUS:
                BaseInfo baseSuc = (BaseInfo) pD[0];
                if (baseSuc.isSuccess()) {
                    showToast("关注成功");
                    mList.get(s).setIs_ftop(1);
                    mAdapter.notifyItemChanged(s);
                }
                break;
        }
    }

    @Override
    public void dataType(int mode) {
        if (mode == LoadTypeConfig.REFRESH) {
            mPresenter.getData(ApiConfig.DATA_GROUP, LoadTypeConfig.REFRESH, 1);
        } else {
            page++;
            mPresenter.getData(ApiConfig.DATA_GROUP, LoadTypeConfig.MORE, page);
        }
    }

    int s;

    @Override
    public void onItemClick(int pos, Object[] pObjects) {
        if (pObjects != null && pObjects.length != 0) {
            switch ((int) pObjects[0]) {
                case ITEM_TYPE:

                    break;
                case FOCUS_TYPE:
                    boolean login = FrameApplication.getFrameApplication().isLogin();
                    if (login){
                    MaterialGroupListEntity entity = mList.get(pos);
                    if (entity.isFocus()) {//已经关注，取消关注
                        s = pos;
                        mPresenter.getData(ApiConfig.CLICK_CANCEL_FOCUS, entity.getGid(), pos);//绿码
                    } else {//没有关注，点击关注
                        s = pos;
                        mPresenter.getData(ApiConfig.CLICK_TO_FOCUS, entity.getGid(), entity.getGroup_name(), pos);
                    }
                    } else {
                        startActivity(new Intent(getContext(), LoginActivity.class).putExtra(JUMP_KEY,DATAGROUPFRAGMENT_TO_LOGIN));
                    }
                    break;
            }
        }
    }
}
