package com.congda.jianxin.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.congda.jianxin.mvp.contract.SplashContract;
import com.congda.jianxin.mvp.model.entity.IMHttpResult;
import com.congda.jianxin.mvp.model.entity.SplashAdBean;
import com.congda.jianxin.mvp.model.entity.VersonBeanData;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


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
public class SplashPresenter extends BasePresenter<SplashContract.Model, SplashContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;


    private int versionCode;// 版本号
    private String versionName;// 版本名

    @Inject
    public SplashPresenter(SplashContract.Model model, SplashContract.View rootView) {
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
     * 获取versioncode 和versionname
     */
    @SuppressLint("CheckResult")
    public void CheckedVersion(Context context) {
        // 获取自己的版本信息
        getMyVersion(context);
        mModel.getVersonData("1")
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(1, 0))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .doOnSubscribe(disposable -> {
                    mRootView.hideLoading();
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new ErrorHandleSubscriber<IMHttpResult<VersonBeanData>>(mErrorHandler) {
                    public void onNext(IMHttpResult<VersonBeanData> versonBeanData) {
                        getSplashData();
                    }
                });
    }

    private void getSplashData() {
        mModel.getGetAdJson()
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(1, 0))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(()->{
                    mRootView.hideLoading();
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new ErrorHandleSubscriber<IMHttpResult<List<SplashAdBean>>>(mErrorHandler) {
                    public void onNext(IMHttpResult<List<SplashAdBean>> s) {
                        if(s.data==null || s.data.size()==0){
                            return;
                        }
                        mRootView.handleView(s.data);
                    }
                });

    }


    /**
     *获取表单请求
     */
    private void getBiaoDanData(Context context) {
        mModel.getBiaoDanData("15528167063","000000")
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(1, 0))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new ErrorHandleSubscriber<Object>(mErrorHandler) {
                    public void onNext(Object versonBeanData) {
                        Log.e("------",versonBeanData.toString() );
                        mRootView.showMessage("2222222");
                    }
                });
    }
    /**
     *获取get请求
     */
    private void getGetData(Context context) {
        mModel.getGetData("0b2bdeda43b5688921839c8ecb20399b")
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(1, 0))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(()->{
                    mRootView.hideLoading();
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new ErrorHandleSubscriber<Object>(mErrorHandler) {
                    public void onNext(Object versonBeanData) {
                        Log.e("------",versonBeanData.toString() );
                        mRootView.showMessage("1111111111");
                    }
                });
    }

    private void getMyVersion(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            // 版本号
            versionCode = packageInfo.versionCode;
            // 版本名
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return;
        }
    }
}
