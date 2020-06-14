package com.cl.model;


import android.content.Context;

import com.cl.frame.ApiConfig;
import com.cl.frame.FrameApplication;
import com.cl.frame.ICommonModule;
import com.cl.frame.ICommonPresenter;
import com.cl.frame.NetManager;
import com.cl.frame.secret.RsaUtil;
import com.cl.frame.utils.ParamHashMap;
import com.cl.the_projext.R;
import com.cl.utils.Application1907;

/**
 * Created by 任小龙 on 2020/6/3.
 */
public class AccountModel implements ICommonModule {
    private NetManager mManger = NetManager.getInstance();
    private Context mContext = Application1907.get07ApplicationContext();
    @Override
    public void getData(ICommonPresenter pPresenter, int whichApi, Object[] params) {
        switch (whichApi) {
            case ApiConfig.SEND_VERIFY:
                mManger.netWork(mManger.getService(mContext.getString(R.string.passport_openapi_user)).getLoginVerify((String) params[0]), pPresenter, whichApi);
                break;
            case ApiConfig.VERIFY_LOGIN:
                mManger.netWork(mManger.getService(mContext.getString(R.string.passport_openapi_user)).loginByVerify(new ParamHashMap().add("mobile",params[0]).add("code",params[1])),pPresenter,whichApi);
                break;
            case ApiConfig.GET_HEADER_INFO:
                String uid = FrameApplication.getFrameApplication().getLoginInfo().getUid();
                mManger.netWork(mManger.getService("https://passport.zhulong.com/api/").getHeaderInfo(new ParamHashMap().add("zuid",uid).add("uid",uid)),pPresenter,whichApi);
                break;
            case ApiConfig.REGISTER_PHONE:
                mManger.netWork(mManger.getService("https://passport.zhulong.com/api/").checkVerifyCode(new ParamHashMap().add("mobile",params[0]).add("code",params[1])),pPresenter,whichApi);
                break;
            case ApiConfig.CHECK_PHONE_IS_USED:
                mManger.netWork(mManger.getService("https://passport.zhulong.com/api/").checkPhoneIsUsed(params[0]),pPresenter,whichApi);
                break;
            case ApiConfig.SEND_REGISTER_VERIFY:
                mManger.netWork(mManger.getService("https://passport.zhulong.com/api/").sendRegisterVerify(params[0]),pPresenter,whichApi);
                break;

            case ApiConfig.NET_CHECK_USERNAME:
                mManger.netWork(mManger.getService("https://passport.zhulong.com/").checkName(params[0]),pPresenter,whichApi);
                break;
            case ApiConfig.COMPLETE_REGISTER_WITH_SUBJECT:
                ParamHashMap param = new ParamHashMap().add("username", params[0]).add("password", RsaUtil.encryptByPublic((String) params[1]))
                        .add("tel", params[2]).add("specialty_id", FrameApplication.getFrameApplication().getSelectedInfo().getSpecialty_id())
                        .add("province_id", 0).add("city_id", 0).add("sex", 0).add("from_reg_name", 0).add("from_reg", 0);
                mManger.netWork(mManger.getService("https://passport.zhulong.com/api/").registerCompleteWithSubject(param),pPresenter,whichApi);
                break;
            case ApiConfig.ACCOUNT_LOGIN:
                ParamHashMap add = new ParamHashMap().add("ZLSessionID", "").add("seccode", "").add("loginName", params[0])
                        .add("passwd", RsaUtil.encryptByPublic((String) params[1])).add("cookieday", "")
                        .add("fromUrl", "android").add("ignoreMobile", "0");
                mManger.netWork(mManger.getService("https://passport.zhulong.com/openapi/").loginByAccount(add),pPresenter,whichApi);
                break;
        }
    }
}
