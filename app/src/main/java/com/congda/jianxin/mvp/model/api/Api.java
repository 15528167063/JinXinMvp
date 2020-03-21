package com.congda.jianxin.mvp.model.api;

import com.congda.jianxin.mvp.model.entity.IMHttpResult;
import com.congda.jianxin.mvp.model.entity.IMImageViewBean;
import com.congda.jianxin.mvp.model.entity.IMUpdataFileBean;
import com.congda.jianxin.mvp.model.entity.SplashAdBean;
import com.congda.jianxin.mvp.model.entity.VersonBeanData;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * ================================================
 * 存放一些与 API 有关的东西,如请求地址,请求码等
 * <p>
 * Created by MVPArmsTemplate on 03/18/2020 18:04
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface Api {
    String APP_DOMAIN = "http://api-cs.clexin.com/";
//    String   APP_DOMAIN = "https://api.douban.com/v2/movie/";
//    String APP_DOMAIN = "http://admin.school.mseenet.com/";
    /**
     *  （通过get请求）https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b
     */
    @GET("in_theaters")
    Observable<Object> login(
            @Query("apikey")String apiKey
    );

    @FormUrlEncoded
    @POST("api/loginPublic")
    Observable<Object> getBiaoDanBean(
            @Field("loginName") String loginName,
            @Field("password") String password
    );
    /**
     *（通过post json请求）
     */
    @POST("tomato-app/commons/check-update")
    Observable<IMHttpResult<VersonBeanData>> getVersonBean(
            @Body RequestBody body
    );
    /**
     *获取广告
     */
    @POST("tomato-app/front-ads/get")
    Observable<IMHttpResult<List<SplashAdBean>>>httpGetAdJson(
    );

    @POST("tomato-app/uploader/simple-image")
    Observable<IMHttpResult<IMImageViewBean>> getUpdataPicture(
            @Body RequestBody body
    );

    @Multipart
    @POST("api/v1/message/uploadFile")
    Observable<Object> getUpdataPictureFile(
            @Part List<MultipartBody.Part> partList
    );
}
