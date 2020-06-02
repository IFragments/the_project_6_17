package com.cl.the_projext;

import androidx.recyclerview.widget.RecyclerView;

import com.cl.adapter.HomeAdapter;
import com.cl.adapter.TestAdapter;
import com.cl.base.BaseMvpActivity;
import com.cl.data.InfoBean;
import com.cl.data.TestInfo;
import com.cl.frame.ApiConfig;
import com.cl.frame.ICommonModule;
import com.cl.frame.LoadTypeConfig;
import com.cl.frame.utils.ParamHashMap;
import com.cl.model.TestModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private TestAdapter mTestAdapter;
    private int pageId = 0;
    private List<TestInfo.DataInfo> datas = new ArrayList<>();
    private Map<String, Object> mParams;


    @Override
    public ICommonModule setModel() {
        return new TestModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setUpView() {
        mParams = new ParamHashMap().add("c", "api").add("a", "getList");
        initRecyclerView(mRecyclerView, mRefreshLayout, mode -> {
            if (mode == LoadTypeConfig.REFRESH){
                pageId = 0;
                mPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.REFRESH, mParams, pageId);
            } else {
                pageId++;
                mPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.MORE, mParams, pageId);
            }
        });

        mTestAdapter = new TestAdapter(datas, this);
        mRecyclerView.setAdapter(mTestAdapter);
    }

    @Override
    public void setUpData() {
        mPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.NORMAL, mParams, pageId);
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi) {
            case ApiConfig.TEST_GET:
                if ((int)pD[0] == LoadTypeConfig.MORE) {
                    mRefreshLayout.finishLoadMore();
                } else if ((int)pD[0] == LoadTypeConfig.REFRESH) {
                    mRefreshLayout.finishRefresh();
                    datas.clear();
                }
                List<TestInfo.DataInfo> datas = ((TestInfo) pD[0]).datas;
                MainActivity.this.datas.addAll(datas);
                mTestAdapter.notifyDataSetChanged();
                break;
        }
    }
//    @BindView(R.id.main_toolbar)
//    Toolbar mainToolbar;
//    @BindView(R.id.main_viewPager)
//    ViewPager mainViewPager;
//    @BindView(R.id.main_tab)
//    TabLayout mainTab;
//    private MaterialFragment materialFragment;
//    private VIPFragment vipFragment;
//    private CurriculumFragment curriculumFragment;
//    private HomeFragment homeFragment;
//    private MyCenterFragment myCenterFragment;
//
//    @Override
//    protected int getLayout() {
//        return R.layout.activity_main;
//    }
//
//    @Override
//    protected void initData() {
//        List<Fragment> data = new ArrayList<>();
//        homeFragment = new HomeFragment();
//        data.add(homeFragment);
//        curriculumFragment = new CurriculumFragment();
//        data.add(curriculumFragment);
//        vipFragment = new VIPFragment();
//        data.add(vipFragment);
//        materialFragment = new MaterialFragment();
//        data.add(materialFragment);
//        myCenterFragment = new MyCenterFragment();
//        data.add(myCenterFragment);
//        ViewPagerScrollAdapter viewPagerScrollAdapter = new ViewPagerScrollAdapter(getSupportFragmentManager(), data);
//        mainViewPager.setAdapter(viewPagerScrollAdapter);
//        mainTab.setupWithViewPager(mainViewPager);
//        mainTab.getTabAt(0).setCustomView(setTabView(R.drawable.tab_home, "首页"));
//        mainTab.getTabAt(1).setCustomView(setTabView(R.drawable.tab_curriculum, "课程"));
//        mainTab.getTabAt(2).setCustomView(setTabView(R.drawable.tab_vip, "VIP"));
//        mainTab.getTabAt(3).setCustomView(setTabView(R.drawable.tab_material, "资料"));
//        mainTab.getTabAt(4).setCustomView(setTabView(R.drawable.tab_mycenter, "我的"));
//    }
//
//    private View setTabView(int selector, String name) {
//        View tabRoot = LayoutInflater.from(this).inflate(R.layout.tab_item_style, null);
//        ImageView imageView = tabRoot.findViewById(R.id.tab_image);
//        TextView textView = tabRoot.findViewById(R.id.tab_text);
//        imageView.setImageResource(selector);
//        textView.setText(name);
//        return tabRoot;
//    }
//
//    @Override
//    protected void initView() {
//        setSupportActionBar(mainToolbar);
//        NoScrollVIewPager noScrollVIewPager = new NoScrollVIewPager(this);
//        noScrollVIewPager.setNoScroll(false);
//    }

}