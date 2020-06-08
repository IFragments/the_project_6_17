package com.cl.the_projext;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cl.adapter.SubjectAdapter;
import com.cl.adapter.SubjectChildAdapter;
import com.cl.base.BaseMvpActivity;
import com.cl.data.BaseInfo;
import com.cl.data.SpecialtyChooseEntity;
import com.cl.frame.ApiConfig;
import com.cl.frame.constants.ConstantKey;
import com.cl.model.LauchModel;
import com.yiyatech.utils.event.NoFinishSubjectEvent;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SubjectActivity extends BaseMvpActivity<LauchModel> implements SubjectChildAdapter.CallbackProAndId {

    private List<SpecialtyChooseEntity> mListData = new ArrayList<>();
    @BindView(R.id.title_content)
    TextView titleContent;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    //    @BindView(R.id.right_image)
//    ImageView rightImage;
    private SubjectAdapter mAdapter;

    @Override
    public LauchModel setModel() {
        return new LauchModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_subject;
    }

    @Override
    public void setUpView() {
        titleContent.setText(getString(R.string.select_subject));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SubjectAdapter(mListData, this);
        recyclerView.setAdapter(mAdapter);
//        EventBus.getDefault().register(this);
    }

    @Override
    public void setUpData() {
        List<SpecialtyChooseEntity> info = SharedPrefrenceUtils.getSerializableList(this, ConstantKey.SUBJECT_LIST);
        if (info != null) {
            mListData.addAll(info);
            mAdapter.notifyDataSetChanged();
        } else {
            mPresenter.getData(ApiConfig.SUBJECT);
        }
    }

    int pro;
    int specialty_id;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void finishThis(NoFinishSubjectEvent noFinishSubject) {
        finish();
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi) {
            case ApiConfig.SUBJECT:
                BaseInfo<List<SpecialtyChooseEntity>> info = (BaseInfo<List<SpecialtyChooseEntity>>) pD[0];
                mListData.addAll(info.result);
                mAdapter.notifyDataSetChanged();
                SharedPrefrenceUtils.putSerializableList(this, ConstantKey.SUBJECT_LIST, mListData);
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPrefrenceUtils.putObject(this, ConstantKey.SUBJECT_SELECT, mApplication.getSelectedInfo());
    }

//    @OnClick({R.id.back_image, R.id.right_image})
    @OnClick(R.id.back_image)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_image:
                break;
//            case R.id.right_image:
//                TransferIDEvent transferIDEvent = new TransferIDEvent(pro, specialty_id);
//                EventBus.getDefault().post(transferIDEvent);
//                break;
        }
        finish();
    }

    @Override
    public void getProAndId(int pro, int spId) {
        this.pro = pro;
        this.specialty_id = spId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }
}
