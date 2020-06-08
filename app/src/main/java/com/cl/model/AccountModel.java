package com.cl.model;


import android.content.Context;

import com.cl.frame.ApiConfig;
import com.cl.frame.FrameApplication;
import com.cl.frame.ICommonModule;
import com.cl.frame.ICommonPresenter;
import com.cl.frame.NetManager;
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
                mManger.netWork(mManger.getService(mContext.getString(R.string.passport_api)).getHeaderInfo(new ParamHashMap().add("zuid",uid).add("uid",uid)),pPresenter,whichApi);
                break;
        }
    }
}
