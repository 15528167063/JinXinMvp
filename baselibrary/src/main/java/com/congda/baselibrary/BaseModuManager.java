package com.congda.baselibrary;

import android.content.Context;

import com.congda.baselibrary.imutils.IMPreferenceUtil;

/**
 * @author jinxin
 * 剑之所指，心之所向
 * @date 2019/8/11
 */
public class BaseModuManager {

    private Context context;
    private static BaseModuManager instance;

    public static BaseModuManager getInstance() {
        if (instance == null) {
            throw new RuntimeException("必须先调用静态方法init(Context context)");
        }
        return instance;
    }

    public static void init(Context context) {
        if (context == null) {
            throw new NullPointerException("context不能为空");
        }
        if(instance == null)
            synchronized (BaseModuManager.class) {
                if(instance == null)
                    instance = new BaseModuManager(context.getApplicationContext());
            }
    }

    /**
     * 初始化module里面的部分属性，并且开启认证身份和开启接受消息的服务
     */
    public BaseModuManager(Context context) {
        this.context = context;
        //初始化PreferenceUtil
        IMPreferenceUtil.init(context);
        //初始化PreferenceUtil表情
    }

    public Context getContext() {
        return context;
    }

}
