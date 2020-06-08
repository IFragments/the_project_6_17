package com.cl.the_projext;

import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

import com.cl.base.BaseSplashActivity;
import com.cl.data.BaseInfo;
import com.cl.data.LoginInfo;
import com.cl.data.MainAdEntity;
import com.cl.data.SpecialtyChooseEntity;
import com.cl.frame.ApiConfig;
import com.cl.frame.ICommonModule;
import com.cl.frame.constants.ConstantKey;
import com.cl.frame.secret.SystemUtils;
import com.cl.model.LauchModel;
import com.yiyatech.utils.newAdd.GlideUtil;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import java.util.concurrent.TimeUnit;

import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GuideActivity extends BaseSplashActivity {
    private BaseInfo<MainAdEntity> mInfo;
    private Disposable mSubscribe;
    private SpecialtyChooseEntity.DataBean mSelectedInfo;

    @Override
    public ICommonModule setModel() {
        return new LauchModel();
    }

    @Override
    public void setUpView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initDevice();
    }

    @Override
    public void setUpData() {
        mSelectedInfo = SharedPrefrenceUtils.getObject(this, ConstantKey.SUBJECT_SELECT);
        String specialtyId = "";
        if (mSelectedInfo != null && !TextUtils.isEmpty(mSelectedInfo.getSpecialty_id())) {
            mApplication.setSelectedInfo(mSelectedInfo);
            specialtyId = mSelectedInfo.getSpecialty_id();
        }
        Point realSize = SystemUtils.getRealSize(this);
        mPresenter.getData(ApiConfig.ADVERT, specialtyId, realSize.x, realSize.y);
        new Handler().postDelayed(()->{ if (mInfo == null)jump(); },3000);
        LoginInfo loginInfo = SharedPrefrenceUtils.getObject(this,ConstantKey.LOGIN_INFO);
        if (loginInfo != null && !TextUtils.isEmpty(loginInfo.getUid()))mApplication.setLoginInfo(loginInfo);
    }

    final int GUIDE_TYPE = 0;
    final int NO_GUIDE = 1;

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        mInfo = (BaseInfo<MainAdEntity>) pD[0];
        if (mInfo != null) {
            GlideUtil.loadImage(advertImage, mInfo.result.getInfo_url());
            timeView.setVisibility(View.VISIBLE);
            goTime(GUIDE_TYPE);
        } else {
            goTime(NO_GUIDE);
        }

    }

    private int preTime = 5;

    private void goTime(int type) {
        switch (type) {
            case GUIDE_TYPE:
                mSubscribe = Observable.interval(1, TimeUnit.SECONDS).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(pLong -> {
                            if (preTime - pLong > 0) {
                                timeView.setText(preTime - pLong + "s");
                                if (preTime - pLong == 1) {
                                    jump();
                                }
                            }
                        });
                break;
            case NO_GUIDE:
                preTime = 3;
                mSubscribe = Observable.interval(1, TimeUnit.SECONDS).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(pLong -> {
                            if (preTime - pLong > 0) {
                                timeView.setText(preTime - pLong + "s");
                                if (preTime - pLong == 1) {
                                    jump();
                                }
                            }
                        });
                break;
        }

    }

    private void jump() {
        if (mSubscribe != null)mSubscribe.dispose();
        startActivity(new Intent(this,mSelectedInfo != null && !TextUtils.isEmpty(mSelectedInfo.getSpecialty_id()) ? mApplication.isLogin() ? HomeActivity.class : LoginActivity.class : SubjectActivity.class ));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscribe != null && !mSubscribe.isDisposed()) mSubscribe.dispose();
    }

    @OnClick({R.id.advert_image, R.id.time_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.advert_image:
                if (mInfo != null) {
//                    mInfo.result.getJump_url();
                }
                break;
            case R.id.time_view:
                jump();
                break;
        }
    }
}