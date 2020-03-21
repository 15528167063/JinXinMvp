package com.congda.jianxin.mvp.model;

import android.app.Application;
import android.util.Log;

import com.congda.jianxin.mvp.contract.SplashContract;
import com.congda.jianxin.mvp.model.api.Api;
import com.congda.jianxin.mvp.model.entity.CommonBean;
import com.congda.jianxin.mvp.model.entity.IMHttpResult;
import com.congda.jianxin.mvp.model.entity.SplashAdBean;
import com.congda.jianxin.mvp.model.entity.VersonBeanData;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 03/18/2020 18:04
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class SplashModel extends BaseModel implements SplashContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public SplashModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    /**
     * get请求
     */
    @Override
    public Observable<Object> getGetData(String appId) {
        return mRepositoryManager.obtainRetrofitService(Api.class).login(appId);
    }
    /**
     * POST表单请求
     */
    @Override
    public Observable<Object> getBiaoDanData(String phone,String password) {
        return mRepositoryManager.obtainRetrofitService(Api.class).getBiaoDanBean(phone,password);
    }
    /**
     * POSTJSON请求
     */
    @Override
    public Observable<IMHttpResult<VersonBeanData>> getVersonData(String systemType) {
        final CommonBean bean = new CommonBean();
        bean.setSystemType("1");
        String json = new Gson().toJson(bean);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Log.e("--getVersonData参数：",json);
        return mRepositoryManager.obtainRetrofitService(Api.class).getVersonBean(body);
    }

    @Override
    public Observable<IMHttpResult<List<SplashAdBean>>> getGetAdJson() {
        return mRepositoryManager.obtainRetrofitService(Api.class).httpGetAdJson();
    }
}