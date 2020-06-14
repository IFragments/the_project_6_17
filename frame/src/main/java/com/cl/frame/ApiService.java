package com.cl.frame;

import com.cl.data.BaseInfo;
import com.cl.data.CourseListInfo;
import com.cl.data.HomeListBean;
import com.cl.data.LoginInfo;
import com.cl.data.MainAdEntity;
import com.cl.data.MaterialGroupListEntity;
import com.cl.data.PersonHeader;
import com.cl.data.SpecialtyChooseEntity;
import com.cl.data.TestInfo;
import com.cl.data.VipBannerBean;
import com.cl.data.VipListBean;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {


    @GET("/")
    Observable<TestInfo> getTestData(@QueryMap Map<String, Object> params, @Query("page_id") int pageId);

    @GET("ad/getAd")
    Observable<BaseInfo<MainAdEntity>> getAdvert(@QueryMap Map<String, Object> pMap);

    @GET("lesson/getAllspecialty")
    Observable<BaseInfo<List<SpecialtyChooseEntity>>> getSubjectList();

    @GET("loginByMobileCode")
    Observable<BaseInfo<String>> getLoginVerify(@Query("mobile") String mobile);

    @GET("loginByMobileCode")
    Observable<BaseInfo<LoginInfo>> loginByVerify(@QueryMap Map<String, Object> params);

    @POST("getUserHeaderForMobile")
    @FormUrlEncoded
    Observable<BaseInfo<PersonHeader>> getHeaderInfo(@FieldMap Map<String, Object> params);
//https://edu.zhulong.com/openapi/lesson/getIndexCommend


    @POST("openapi/lesson/getCarouselphoto")
    @FormUrlEncoded
    Observable<JsonObject> getHomeBannerAndLive(@FieldMap Map<String, Object> map);

    @POST("openapi/lesson/getIndexCommend")
    @FormUrlEncoded
    Observable<HomeListBean> getHomeList(@FieldMap Map<String, Object> map);


    @POST("lesson/getLessonListForApi")
    @FormUrlEncoded
    Observable<BaseInfo<CourseListInfo>> getCourseChildData(@FieldMap Map<String, Object> map);


    @POST("group/getGroupList")
    @FormUrlEncoded
    Observable<BaseInfo<List<MaterialGroupListEntity>>> getGroupList(@FieldMap Map<String, Object> params);

    @POST("removeGroup")
    @FormUrlEncoded
    Observable<BaseInfo> removeFocus(@FieldMap Map<String, Object> params);

    @POST("joingroup")
    @FormUrlEncoded
    Observable<BaseInfo> focus(@FieldMap Map<String, Object> params);

    @POST("checkMobileCode")
    @FormUrlEncoded
    Observable<BaseInfo> checkVerifyCode(@FieldMap Map<String, Object> params);

    @POST("checkMobileIsUse")
    @FormUrlEncoded
    Observable<BaseInfo> checkPhoneIsUsed(@Field("mobile") Object mobile);

    @POST("sendMobileCode")
    @FormUrlEncoded
    Observable<BaseInfo> sendRegisterVerify(@Field("mobile") Object mobile);

    @POST("user/usernameIsExist")
    @FormUrlEncoded
    Observable<BaseInfo> checkName(@Field("username") Object mobile);

    @POST("userRegForSimple")
    @FormUrlEncoded
    Observable<BaseInfo> registerCompleteWithSubject(@FieldMap Map<String, Object> params);

    @POST("user/userLoginNewAuth")
    @FormUrlEncoded
    Observable<BaseInfo<LoginInfo>> loginByAccount(@FieldMap Map<String, Object> params);

    @POST("get_new_vip")
    @FormUrlEncoded
    Observable<VipBannerBean> getVipBanner(@FieldMap Map<String, Object> params);

    @POST("getVipSmallLessonList")
    @FormUrlEncoded
    Observable<VipListBean> getVipList(@FieldMap Map<String, Object> params);
}
