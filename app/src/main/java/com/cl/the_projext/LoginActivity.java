package com.cl.the_projext;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cl.base.BaseMvpActivity;
import com.cl.constants.JumpConstant;
import com.cl.data.BaseInfo;
import com.cl.data.LoginInfo;
import com.cl.data.PersonHeader;
import com.cl.frame.ApiConfig;
import com.cl.frame.constants.ConstantKey;
import com.cl.model.AccountModel;
import com.cl.view.LoginView;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.cl.constants.JumpConstant.SPLASH_TO_LOGIN;
import static com.cl.constants.JumpConstant.SUB_TO_LOGIN;

public class LoginActivity extends BaseMvpActivity<AccountModel> implements LoginView.LoginViewCallBack {

    @BindView(R.id.login_view)
    LoginView mLoginView;
    private Disposable mSubscribe;
    private String phoneNum;
    private String mFromType;

    @Override
    public AccountModel setModel() {
        return new AccountModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void setUpView() {
        mFromType = getIntent().getStringExtra(JumpConstant.JUMP_KEY);
        mLoginView.setLoginViewCallBack(this);
    }

    @Override
    public void setUpData() {

    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi) {
            case ApiConfig.SEND_VERIFY:
                BaseInfo<String> info = (BaseInfo<String>) pD[0];
                if (info.isSuccess()) {
                    showToast(info.result);
                    goTime();
                } else {
                    showToast("发送太频繁啦,等一下啦");
                }
                break;
            case ApiConfig.ACCOUNT_LOGIN:
            case ApiConfig.VERIFY_LOGIN:
                BaseInfo<LoginInfo> baseInfo = (BaseInfo<LoginInfo>) pD[0];
                LoginInfo loginInfo = baseInfo.result;
                if (!TextUtils.isEmpty(phoneNum)) loginInfo.login_name = phoneNum;
                mApplication.setLoginInfo(loginInfo);
                mPresenter.getData(ApiConfig.GET_HEADER_INFO);
                break;
            case ApiConfig.GET_HEADER_INFO:
                PersonHeader personHeader = ((BaseInfo<PersonHeader>) pD[0]).result;
                mApplication.getLoginInfo().personHeader = personHeader;
                SharedPrefrenceUtils.putObject(this, ConstantKey.LOGIN_INFO, mApplication.getLoginInfo());
                jump();
                break;
        }
    }

    private void jump() {
        if (!TextUtils.isEmpty(mFromType) && mFromType.equals(SPLASH_TO_LOGIN) || mFromType.equals(SUB_TO_LOGIN))
            startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    private long time = 59l;

    private void goTime() {
        mSubscribe = Observable.interval(1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(goTime -> {
            mLoginView.getVerifyCode.setText(time - goTime + "s");
            if (time - goTime < 1) doPre();
        });
    }

    private void doPre() {
        if (mSubscribe != null && !mSubscribe.isDisposed()) mSubscribe.dispose();
        mLoginView.getVerifyCode.setText("获取验证码");
    }

    @OnClick({R.id.close_login, R.id.register_press, R.id.forgot_pwd, R.id.login_by_qq, R.id.login_by_wx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close_login:
                if (!TextUtils.isEmpty(mFromType) && (mFromType.equals(SUB_TO_LOGIN) || mFromType.equals(SPLASH_TO_LOGIN))) {
                    startActivity(new Intent(this, HomeActivity.class));
                }
                finish();
                break;
            case R.id.register_press:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.forgot_pwd:

                break;
            case R.id.login_by_qq:
                break;
            case R.id.login_by_wx:
                break;
        }
    }

    @Override
    public void sendVerifyCode(String phoneNum) {
        this.phoneNum = phoneNum;
        mPresenter.getData(ApiConfig.SEND_VERIFY, phoneNum);
    }

    @Override
    public void loginPress(int type, String userName, String pwd) {
        doPre();
        if (mLoginView.mCurrentLoginType == mLoginView.VERIFY_TYPE)
            mPresenter.getData(ApiConfig.VERIFY_LOGIN, userName, pwd);
        else mPresenter.getData(ApiConfig.ACCOUNT_LOGIN, userName, pwd);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doPre();
    }
}
