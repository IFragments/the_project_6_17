package com.cl.model;


import com.cl.frame.ICommonModule;
import com.cl.frame.ICommonPresenter;
import com.cl.frame.NetManager;

import java.util.HashMap;

public class MainPageModel implements ICommonModule {
    private NetManager manager = NetManager.getInstance();

    @Override
    public void getData(ICommonPresenter pPresenter, int whichApi, Object[] params) {
//        manager.netWork(manager.getService("https://baidu/com/").getHeaderInfo(new HashMap<>()), pPresenter, whichApi);
//        manager.netWork(manager.getService("https://baidu/com/com/").getHeaderInfo(new HashMap<>()), pPresenter, whichApi);
    }
}
