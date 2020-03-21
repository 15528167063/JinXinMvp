package com.congda.jianxin.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.congda.baselibrary.imutils.IMCutTimeDownView;
import com.congda.baselibrary.imutils.IMStatusBarUtil;
import com.congda.jianxin.R;
import com.congda.jianxin.app.utils.glide.IMImageLoadUtil;
import com.congda.jianxin.di.component.DaggerSplashComponent;
import com.congda.jianxin.mvp.contract.SplashContract;
import com.congda.jianxin.mvp.model.entity.SplashAdBean;
import com.congda.jianxin.mvp.model.entity.VersonBeanData;
import com.congda.jianxin.mvp.presenter.SplashPresenter;
import com.congda.jianxin.mvp.ui.IMBaseActivity;
import com.jess.arms.di.component.AppComponent;

import java.util.List;

import butterknife.BindView;


public class SplashActivity extends IMBaseActivity<SplashPresenter> implements SplashContract.View, IMCutTimeDownView.OnFinishListener {
    @BindView(R.id.skipTv)
    IMCutTimeDownView skipTv;
    @BindView(R.id.iv_bg)
    ImageView mIvBg;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerSplashComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_splash; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initStatusBar() {
        IMStatusBarUtil.setTranslucentForImageView(this,0,null);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        skipTv.setOnFinishListener(this);
        mPresenter.CheckedVersion(this);
    }


    @Override
    public void setOnFinishListener() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void handleView(List<SplashAdBean> s) {
        skipTv.setTotalTime(3000);
        IMImageLoadUtil.CommonSplashImageLoadCp(this,s.get(0).getAdsImgUrl(),mIvBg);

    }
}
