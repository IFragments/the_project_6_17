package com.cl.model;

import com.cl.frame.ApiConfig;
import com.cl.frame.ICommonModule;
import com.cl.frame.ICommonPresenter;
import com.cl.frame.NetManager;

import java.util.Map;

public class MainFModule implements ICommonModule {
    NetManager netManager = NetManager.getInstance();

    //https://edu.zhulong.com/
    @Override
    public void getData(ICommonPresenter pPresenter, int whichApi, Object[] params) {
        switch (whichApi) {
            case ApiConfig.HOME_MAIN_FRAGMENT_BANNER_AND_MORE_LIVE:
                netManager.netWork(netManager.getService2("https://edu.zhulong.com/").getHomeBannerAndLive((Map<String, Object>) params[0]), pPresenter, whichApi);
                break;
            case ApiConfig.HOME_MAIN_FRAGMENT:
                netManager.netWork(netManager.getService2("https://edu.zhulong.com/").getHomeList((Map<String, Object>) params[0]), pPresenter, whichApi);
                break;
        }
    }
}
