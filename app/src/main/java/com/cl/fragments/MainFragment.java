package com.cl.fragments;

import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.cl.adapter.HomeMainAdapter;
import com.cl.base.BaseMvpFragment;
import com.cl.data.HomeListBean;
import com.cl.data.HomeMainBean;
import com.cl.data.SpecialtyChooseEntity;
import com.cl.frame.ApiConfig;
import com.cl.frame.ICommonModule;
import com.cl.frame.LoadTypeConfig;
import com.cl.frame.constants.ConstantKey;
import com.cl.frame.utils.ParamHashMap;
import com.cl.interfaces.DataListener;
import com.cl.model.MainFModule;
import com.cl.the_projext.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yiyatech.utils.event.NoFinishSubjectEvent;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

public class MainFragment extends BaseMvpFragment<ICommonModule> implements DataListener {

    @BindView(R.id.recyclerView_home_main)
    RecyclerView homeMainRecycle;
    @BindView(R.id.sm)
    SmartRefreshLayout smartRefreshLayout;
    private HomeMainAdapter homeMainAdapter;
    private ParamHashMap addList;
    private ParamHashMap add;
    private int fid;
    private int spId;

    @Override
    public ICommonModule setModel() {
        return new MainFModule();
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_main_page;
    }

    @Override
    public void setUpView() {
        initRecyclerView(homeMainRecycle, smartRefreshLayout, this);
        homeMainAdapter = new HomeMainAdapter(context);
        homeMainRecycle.setAdapter(homeMainAdapter);
//        EventBus.getDefault().register(this);
    }

    int page = 1;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void setSubject(NoFinishSubjectEvent noFinishSubjectEvent) {
        int pro = noFinishSubjectEvent.getPro();
        int spId = noFinishSubjectEvent.getSpId();
        fid = pro;
        this.spId = spId;
        page = 1;
        mPresenter.getData(ApiConfig.HOME_MAIN_FRAGMENT_BANNER_AND_MORE_LIVE, add);
        mPresenter.getData(ApiConfig.HOME_MAIN_FRAGMENT, addList);
    }

    @Override
    public void setUpData() {
        SpecialtyChooseEntity.DataBean specialtyChooseEntity = SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.SUBJECT_SELECT);
        fid = specialtyChooseEntity.getFid();
        String specialty_id = specialtyChooseEntity.getSpecialty_id();
        spId = Integer.parseInt(specialty_id);
        add = new ParamHashMap()
                .add("pro", fid + "")
                .add("more_live", 1)
                .add("new_banner", 1)
                .add("is_new", 1)
                .add("page", page);
        addList = new ParamHashMap()
                .add("pro", 1 + "")
                .add("more_live", 1)
                .add("page", page)
                .add("new_banner", 1)
                .add("is_new", 1)
                .add("limit", 10)
                .add("specialty_id", specialty_id + "");
        mPresenter.getData(ApiConfig.HOME_MAIN_FRAGMENT_BANNER_AND_MORE_LIVE, add);
        mPresenter.getData(ApiConfig.HOME_MAIN_FRAGMENT, addList);
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi) {
            case ApiConfig.HOME_MAIN_FRAGMENT_BANNER_AND_MORE_LIVE:
//                HomeMainBean homeMainBeanBaseInfo = (HomeMainBean) pD[0];
                JsonObject jsonObjects = (JsonObject) pD[0];
//                JsonObject jsonObject = (JsonObject) pD[0];
                HomeMainBean homeMainBean = null;
                try {
                    String s = jsonObjects.toString();
                    Log.d(TAG, "netSuccess: adadsdsada" + s);
                    JSONObject object = new JSONObject(s);
                    if (object.getString("errNo").equals("0")) {
                        String result = object.getString("result");
                        JSONObject object1 = new JSONObject(result);
                        String live = object1.getString("live");
                        if (live.equals("true") || live.equals("false")) {
                            object1.remove("live");
                        }
                        result = object1.toString();
                        homeMainBean = new Gson().fromJson(result, HomeMainBean.class);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                List<HomeMainBean.Carousel> carousel = homeMainBean.Carousel;
                List<HomeMainBean.Live> live = homeMainBean.live;
                showLog(homeMainBean);
                homeMainAdapter.setReBannerData(carousel);
                homeMainAdapter.setReLiveData(live);
                break;
            case ApiConfig.HOME_MAIN_FRAGMENT:
                HomeListBean listBeanBaseInfo = (HomeListBean) pD[0];
                if (listBeanBaseInfo == null || listBeanBaseInfo.getResult().size() == 0) {
                    Toast.makeText(context, "阿秀特", Toast.LENGTH_SHORT).show();
                    return;
                }
                int size = listBeanBaseInfo.getResult().size();
                Log.d(TAG, "netSuccess: " + size);
                if (page == 1) {
                    homeMainAdapter.setReListData(listBeanBaseInfo.getResult());
                } else {
                    homeMainAdapter.setListData(listBeanBaseInfo.getResult());
                }
                break;
        }
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        EventBus.getDefault().unregister(this);
    }

    private static final String TAG = "MainFragment";

    @Override
    public void dataType(int mode) {
        switch (mode) {
            case LoadTypeConfig.REFRESH:
                page = 1;
                mPresenter.getData(ApiConfig.HOME_MAIN_FRAGMENT_BANNER_AND_MORE_LIVE, add);
                mPresenter.getData(ApiConfig.HOME_MAIN_FRAGMENT, addList);
                break;
            case LoadTypeConfig.MORE:
                page++;
                mPresenter.getData(ApiConfig.HOME_MAIN_FRAGMENT_BANNER_AND_MORE_LIVE, add);
                mPresenter.getData(ApiConfig.HOME_MAIN_FRAGMENT, addList);
                break;
        }
    }
}
