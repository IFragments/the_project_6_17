package com.cl.fragments;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cl.adapter.VipAdapter;
import com.cl.base.BaseMvpFragment;
import com.cl.data.SpecialtyChooseEntity;
import com.cl.data.VipBannerBean;
import com.cl.data.VipListBean;
import com.cl.frame.ApiConfig;
import com.cl.frame.ICommonModule;
import com.cl.frame.LoadTypeConfig;
import com.cl.frame.constants.ConstantKey;
import com.cl.frame.utils.ParamHashMap;
import com.cl.interfaces.DataListener;
import com.cl.model.VipModule;
import com.cl.the_projext.R;
import com.google.gson.JsonObject;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

public class VIPFragment extends BaseMvpFragment implements DataListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    ParamHashMap bannerAndLiveMap;
    ParamHashMap listMap;
    private String specialty_id;
    private VipAdapter vipAdapter;

    @Override
    public ICommonModule setModel() {
        return new VipModule();
    }

    @Override
    public int setLayoutId() {
        return R.layout.refresh_list_layout;
    }

    @Override
    public void setUpView() {
        initRecyclerView(recyclerView, refreshLayout, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        vipAdapter = new VipAdapter(context);
        recyclerView.setAdapter(vipAdapter);
    }

    @Override
    public void setUpData() {
        SpecialtyChooseEntity.DataBean specialtyChooseEntity = SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.SUBJECT_SELECT);
        fid = specialtyChooseEntity.getFid();
        specialty_id = specialtyChooseEntity.getSpecialty_id();
        bannerAndLiveMap = new ParamHashMap().add("pro", fid);
        listMap = new ParamHashMap()
                .add("pro", fid + "")
                .add("more_live", 1)
                .add("page", page)
                .add("new_banner", 1)
                .add("is_new", 1)
                .add("limit", 10)
                .add("specialty_id", specialty_id + "");
        mPresenter.getData(ApiConfig.HOME_MAIN_FRAGMENT_BANNER_AND_MORE_LIVE, bannerAndLiveMap);
        mPresenter.getData(ApiConfig.HOME_MAIN_FRAGMENT, listMap);
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi) {
            case ApiConfig.VIP_FRAGMENT_BANNER_AND_LIVE:
                VipBannerBean v = (VipBannerBean) pD[0];
                if (v == null) return;
                List<VipBannerBean.ResultBean.LiveBeanX.LiveBean> live = v.getResult().getLive().getLive();
                List<VipBannerBean.ResultBean.LunbotuBean> banner = v.getResult().getLunbotu();
                vipAdapter.setLiveData(live);
                vipAdapter.setBannerData(banner);
                break;
            case ApiConfig.VIP_FRAGMENT:
                VipListBean vip = (VipListBean) pD[0];
                if (vip == null) return;
                List<VipListBean.ResultBean.ListBean> list = vip.getResult().getList();
                if (page == 2) {
                    vipAdapter.isRefresh = 1;
                    vipAdapter.setListData(list);
                    refreshLayout.finishRefresh();
                } else {
                    vipAdapter.isRefresh = 2;
                    vipAdapter.setListData(list);
                    refreshLayout.finishLoadMore();
                }
                break;
        }
    }

    int page = 2;
    int fid;

    @Override
    public void dataType(int mode) {
        switch (mode) {
            case LoadTypeConfig.REFRESH:
                page = 2;
                mPresenter.getData(ApiConfig.VIP_FRAGMENT, listMap);
                mPresenter.getData(ApiConfig.VIP_FRAGMENT_BANNER_AND_LIVE, bannerAndLiveMap);
                break;
            case LoadTypeConfig.MORE:
                page++;
                mPresenter.getData(ApiConfig.VIP_FRAGMENT, listMap);
                mPresenter.getData(ApiConfig.VIP_FRAGMENT_BANNER_AND_LIVE, bannerAndLiveMap);
                break;
        }
    }
}
