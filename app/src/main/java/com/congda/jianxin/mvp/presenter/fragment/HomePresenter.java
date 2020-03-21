package com.congda.jianxin.mvp.presenter.fragment;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.congda.baselibrary.imutils.IMPreferenceUtil;
import com.congda.jianxin.application.Constanst;
import com.congda.jianxin.mvp.model.entity.IMHttpResult;
import com.congda.jianxin.mvp.model.entity.IMImageViewBean;
import com.congda.jianxin.mvp.model.entity.VersonBeanData;
import com.google.gson.Gson;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import javax.inject.Inject;

import com.congda.jianxin.mvp.contract.fragment.HomeContract;
import com.jess.arms.utils.RxLifecycleUtils;

import java.io.File;
import java.util.List;


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
public class HomePresenter extends BasePresenter<HomeContract.Model, HomeContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public HomePresenter(HomeContract.Model model, HomeContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
    /**
     * 上传图片(base64)
     */
    public void updataPickture(List<String> mSelected) {
        for (int i = 0; i < mSelected.size(); i++) {
            mModel.getUpdataPicture(mSelected.get(i))
                    .subscribeOn(Schedulers.io())
                    .retryWhen(new RetryWithDelay(1, 0))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                    .doOnSubscribe(disposable -> {
                        mRootView.showLoading();
                    })
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally(()->
                            mRootView.hideLoading()
                    )
                    .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                    .subscribe(new ErrorHandleSubscriber<IMHttpResult<IMImageViewBean>>(mErrorHandler) {
                        public void onNext(IMHttpResult<IMImageViewBean> bean) {
                            if(bean.data==null){
                                mRootView.showMessage(bean.message);
                                IMPreferenceUtil.setPreference_String(Constanst.SAVE_TOKEN,"AE6DF090486DE564369C0DB5FA454321");
                                return;
                            }
                          mRootView.setChooseData(bean.data.getUrl());
                        }
                    });
        }
    }
    /**
     * 上传图片(文件上传)
     */
    public void updataPicktureFile(List<String> mSelected) {
        for (int i = 0; i < mSelected.size(); i++) {
            List<MultipartBody.Part> parts=getParts(mSelected.get(i));
            mModel.getUpdataPictureFile(parts)
                    .subscribeOn(Schedulers.io())
                    .retryWhen(new RetryWithDelay(1, 0))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                    .doOnSubscribe(disposable -> {
                        mRootView.showLoading();
                    })
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally(()->
                            mRootView.hideLoading()
                    )
                    .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                    .subscribe(new ErrorHandleSubscriber<Object>(mErrorHandler) {
                        public void onNext(Object bean) {
                            if(bean==null){
                                return;
                            }
                            Log.e("----文件上传-",bean.toString());
                        }
                    });
        }
    }


    /**
     * 构建上传对象
     */
    private List<MultipartBody.Part> getParts(String path) {
        File file = new File(path);
        //1.创建MultipartBody.Builder对象
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);

        //2.获取图片，创建请求体
//        RequestBody body=RequestBody.create(MediaType.parse("multipart/form-data"),file_name);//表单类型
        RequestBody body=RequestBody.create(MediaType.parse("application/otcet-stream"),file);

        //3.调用MultipartBody.Builder的addFormDataPart()方法添加表单数据
        builder.addFormDataPart("file",file.getName(),body); //添加图片数据，body创建的请求体
        builder.addFormDataPart("access_token", "f2ec0614-7c6f-4767-8b53-7a898a55a18b");
        //4.创建List<MultipartBody.Part> 集合，
        //  调用MultipartBody.Builder的build()方法会返回一个新创建的MultipartBody
        //  再调用MultipartBody的parts()方法返回MultipartBody.Part集合
       return builder.build().parts();
    }

}
