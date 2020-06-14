package com.cl.model;

import com.cl.frame.ApiConfig;
import com.cl.frame.ICommonModule;
import com.cl.frame.ICommonPresenter;
import com.cl.frame.NetManager;

import java.util.Map;

public class VipModule implements ICommonModule {
    NetManager instance = NetManager.getInstance();

    @Override
    public void getData(ICommonPresenter pPresenter, int whichApi, Object[] params) {
        switch (whichApi){
            case ApiConfig.VIP_FRAGMENT:
                //
                instance.netWork(instance.getService("https://edu.zhulong.com/openapi/lesson/").getVipList((Map<String, Object>) params[0]),pPresenter,whichApi);
                break;
            case ApiConfig.VIP_FRAGMENT_BANNER_AND_LIVE:
                //
                instance.netWork(instance.getService("https://edu.zhulong.com/openapi/lesson/").getVipBanner((Map<String, Object>) params[0]),pPresenter,whichApi);
                break;
        }
    }
}
