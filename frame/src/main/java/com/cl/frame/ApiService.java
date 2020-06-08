package com.cl.frame;

import com.cl.data.BaseInfo;
import com.cl.data.HomeListBean;
import com.cl.data.HomeMainBean;
import com.cl.data.InfoBean;
import com.cl.data.LoginInfo;
import com.cl.data.MainAdEntity;
import com.cl.data.PersonHeader;
import com.cl.data.SpecialtyChooseEntity;
import com.cl.data.TestInfo;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
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
}
