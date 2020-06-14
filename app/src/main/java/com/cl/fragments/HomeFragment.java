package com.cl.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import com.cl.base.BaseMvpFragment;
import com.cl.constants.JumpConstant;
import com.cl.design.BottomTabView;
import com.cl.frame.FrameApplication;
import com.cl.model.MainPageModel;
import com.cl.the_projext.R;
import com.cl.the_projext.SubjectActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseMvpFragment implements BottomTabView.OnBottomTabClickCallBack, NavController.OnDestinationChangedListener, View.OnClickListener {
    private List<Integer> normalIcon = new ArrayList<>();//为选中的icon
    private List<Integer> selectedIcon = new ArrayList<>();// 选中的icon
    private List<String> tabContent = new ArrayList<>();//tab对应的内容
    protected NavController mHomeController;
    private final int MAIN_PAGE = 1, COURSE = 2, VIP = 3, DATA = 4, MINE = 5;

    @Override
    public MainPageModel setModel() {
        return null;
    }

    @Override
    public int setLayoutId() {
        return R.layout.home_fragment;
    }

    @BindView(R.id.select_subject)
    TextView mTextView;

    @Override
    public void setUpView() {
        String specialty_name = frameApplication.getSelectedInfo().getSpecialty_name();
        mTextView.setText(specialty_name);
        BottomTabView tabView = getView().findViewById(R.id.bottom_tab);
        Collections.addAll(normalIcon, R.drawable.main_page_view, R.drawable.course_view, R.drawable.vip_view, R.drawable.data_view, R.drawable.mine_view);
        Collections.addAll(selectedIcon, R.drawable.main_selected, R.drawable.course_selected, R.drawable.vip_selected, R.drawable.data_selected, R.drawable.mine_selected);
        Collections.addAll(tabContent, "主页", "课程", "VIP", "资料", "我的");
        tabView.setResource(normalIcon, selectedIcon, tabContent);
        tabView.setOnBottomTabClickCallBack(this);
        mTextView.setOnClickListener(this);
//        EventBus.getDefault().register(this);
    }

//    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
//    public void setEventData(TransferIDEvent transferIDEvent) {
//        String name = transferIDEvent.getName();
//        mTextView.setText(name);
//        int pro = transferIDEvent.getPro();
//        int spId = transferIDEvent.getSpId();
//    }

    @Override
    public void setUpData() {
        mHomeController = Navigation.findNavController(getView().findViewById(R.id.home_fragment_container));
        mHomeController.addOnDestinationChangedListener(this);
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mTextView.setText(FrameApplication.getFrameApplication().getSelectedInfo().getSpecialty_name());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        normalIcon.clear();
        selectedIcon.clear();
        tabContent.clear();
//        EventBus.getDefault().unregister(this);
    }

    @Override
    public void clickTab(int tab) {
        switch (tab) {
            case MAIN_PAGE:
                mHomeController.navigate(R.id.mainPageFragment);
                break;
            case COURSE:
                mHomeController.navigate(R.id.courseFragment);
                break;
            case VIP:
                mHomeController.navigate(R.id.vipFragment);
                break;
            case DATA:
                mHomeController.navigate(R.id.dataFragment);
                break;
            case MINE:
                mHomeController.navigate(R.id.mineFragment);
                break;
        }
    }


    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        showLog(destination.getLabel().toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_subject:
                startActivity(new Intent(getContext(), SubjectActivity.class).putExtra(JumpConstant.JUMP_KEY, JumpConstant.HOME_TO_SUB));
                break;
        }
    }
}
