package com.congda.jianxin.mvp.contract;

import com.congda.jianxin.mvp.model.entity.IMHttpResult;
import com.congda.jianxin.mvp.model.entity.SplashAdBean;
import com.congda.jianxin.mvp.model.entity.VersonBeanData;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.List;

import io.reactivex.Observable;


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
public interface SplashContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void handleView(List<SplashAdBean> s);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<IMHttpResult<VersonBeanData>> getVersonData(String systemType);

        Observable<IMHttpResult<List<SplashAdBean>>> getGetAdJson();
        //网络请求get方式
        Observable<Object> getGetData(String appId);
        //网络请求表单方式
        Observable<Object> getBiaoDanData(String phone,String psd);
    }
}
