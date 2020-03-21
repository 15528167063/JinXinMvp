package com.congda.jianxin.mvp.model.fragment;

import android.app.Application;
import android.util.Log;

import com.congda.baselibrary.imutils.IMBase64ImageUtils;
import com.congda.jianxin.mvp.model.api.Api;
import com.congda.jianxin.mvp.model.entity.CommonBean;
import com.congda.jianxin.mvp.model.entity.IMHttpResult;
import com.congda.jianxin.mvp.model.entity.IMImageViewBean;
import com.congda.jianxin.mvp.model.entity.VersonBeanData;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.FragmentScope;

import javax.inject.Inject;

import com.congda.jianxin.mvp.contract.fragment.HomeContract;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 03/19/2020 10:16
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
public class HomeModel extends BaseModel implements HomeContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public HomeModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    /**
     * POSTJSON请求
     */
    @Override
    public Observable<IMHttpResult<IMImageViewBean>> getUpdataPicture(String url) {
        final CommonBean bean = new CommonBean();
        bean.setBase64Data(IMBase64ImageUtils.imageToBase64(url));
        String json = new Gson().toJson(bean);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        return mRepositoryManager.obtainRetrofitService(Api.class).getUpdataPicture(body);
    }

    @Override
    public Observable<Object> getUpdataPictureFile( List<MultipartBody.Part> parts) {
        return mRepositoryManager.obtainRetrofitService(Api.class).getUpdataPictureFile(parts);
    }
}