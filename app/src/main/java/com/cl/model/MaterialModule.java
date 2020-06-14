package com.cl.model;

import com.cl.frame.ApiConfig;
import com.cl.frame.FrameApplication;
import com.cl.frame.ICommonModule;
import com.cl.frame.ICommonPresenter;
import com.cl.frame.NetManager;
import com.cl.frame.utils.ParamHashMap;
import com.cl.the_projext.R;

import java.util.Map;

public class MaterialModule implements ICommonModule {
    NetManager mManger = NetManager.getInstance();

    @Override
    public void getData(ICommonPresenter pPresenter, int whichApi, Object[] params) {
        switch (whichApi) {
            case ApiConfig.DATA_GROUP:
                ParamHashMap add = new ParamHashMap().add("type", 1).add("fid", FrameApplication.getFrameApplication().getSelectedInfo().getFid()).add("page", params[1]);//                mManger.netWork(mManger.getService());
                mManger.netWork(mManger.getService("https://bbs.zhulong.com/openapi/").getGroupList(add), pPresenter, whichApi);
                break;
            case ApiConfig.CLICK_CANCEL_FOCUS:
                add = new ParamHashMap().add("group_id", params[0]).add("type", 1).add("screctKey", FrameApplication.getFrameApplicationContext().getString(R.string.secrectKey_posting));
                mManger.netWork(mManger.getService("https://bbs.zhulong.com/api/").removeFocus(add), pPresenter, whichApi);
                break;
            case ApiConfig.CLICK_TO_FOCUS:
                add = new ParamHashMap().add("gid", params[0]).add("group_name", params[1]).add("screctKey", FrameApplication.getFrameApplicationContext().getString(R.string.secrectKey_posting));
                NetManager.getInstance().netWork(NetManager.getInstance().getService("https://bbs.zhulong.com/api/").focus(add),pPresenter,whichApi,params[2]);
                break;
        }
    }
}
