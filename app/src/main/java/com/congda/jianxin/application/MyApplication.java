package com.congda.jianxin.application;

import android.content.Context;

import com.congda.baselibrary.BaseModuManager;
import com.congda.baselibrary.imutils.IMCrashHandler;
import com.congda.baselibrary.imutils.IMPreferenceUtil;
import com.jess.arms.base.BaseApplication;

public class MyApplication extends BaseApplication {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        this.context=getApplicationContext();
        BaseModuManager.init(this);
        IMCrashHandler crashHandler = IMCrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }

    public static Context getContext() {
        return context;
    }
}