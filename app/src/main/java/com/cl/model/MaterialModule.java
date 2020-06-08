package com.cl.model;

import com.cl.frame.ApiConfig;
import com.cl.frame.ICommonModule;
import com.cl.frame.ICommonPresenter;
import com.cl.frame.NetManager;

public class MaterialModule implements ICommonModule {
    NetManager mManger = NetManager.getInstance();
    @Override
    public void getData(ICommonPresenter pPresenter, int whichApi, Object[] params) {
        switch (whichApi) {
            case ApiConfig.MATERIAL_FRAGMENT:
//                mManger.netWork(mManger.getService());
                break;
        }
    }
}
