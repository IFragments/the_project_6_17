package com.cl.model;

import com.cl.frame.ApiConfig;
import com.cl.frame.FrameApplication;
import com.cl.frame.ICommonModule;
import com.cl.frame.ICommonPresenter;
import com.cl.frame.NetManager;
import com.cl.frame.constants.Constants;
import com.cl.frame.utils.ParamHashMap;

import java.lang.reflect.Method;
import java.util.HashMap;

public class CurriculumModule implements ICommonModule {
    private String subjectId = FrameApplication.getFrameApplication().getSelectedInfo().getSpecialty_id();
    NetManager instance = NetManager.getInstance();
    @Override
    public void getData(ICommonPresenter pPresenter, int whichApi, Object[] params) {
        switch (whichApi){
            case ApiConfig.COURSE_CHILD:
                ParamHashMap add = new ParamHashMap().add("specialty_id", subjectId).add("page", params[2]).add("limit", Constants.LIMIT_NUM).add("course_type", params[1]);
//                NetManager.getInstance().netWork(NetManager.mService.getCourseChildData(Host.EDU_OPENAPI+ Method.GETLESSONLISTFORAPI,add),pPresenter,whichApi,params[0]);
                instance.netWork(instance.getService("https://edu.zhulong.com/openapi/").getCourseChildData(add), pPresenter, whichApi);
                break;

        }
    }
}
