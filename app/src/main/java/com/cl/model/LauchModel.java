package com.cl.model;

import android.content.Context;
import android.text.TextUtils;

import com.cl.frame.ApiConfig;
import com.cl.frame.ICommonModule;
import com.cl.frame.ICommonPresenter;
import com.cl.frame.NetManager;
import com.cl.frame.utils.ParamHashMap;
import com.cl.the_projext.R;
import com.cl.utils.Application1907;

public class LauchModel implements ICommonModule {
    private NetManager mManger = NetManager.getInstance();
    private Context mContext = Application1907.get07ApplicationContext();

    @Override
    public void getData(ICommonPresenter pPresenter, int whichApi, Object[] params) {
        switch (whichApi) {
            case ApiConfig.ADVERT:
                ParamHashMap map = new ParamHashMap().add("w", params[1]).add("h", params[2]).add("positions_id", "APP_QD_01").add("is_show", 0);
                if (!TextUtils.isEmpty((String) params[0])) map.add("specialty_id", params[0]);
                mManger.netWork(mManger.getService(mContext.getString(R.string.ad_openapi)).getAdvert(map), pPresenter, whichApi);
                break;
            case ApiConfig.SUBJECT:
                mManger.netWork(mManger.getService(mContext.getString(R.string.edu_openapi)).getSubjectList(), pPresenter, whichApi);
                break;
        }
    }
}
