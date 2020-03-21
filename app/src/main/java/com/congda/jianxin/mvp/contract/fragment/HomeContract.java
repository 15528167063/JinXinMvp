package com.congda.jianxin.mvp.contract.fragment;

import com.congda.jianxin.mvp.model.entity.IMHttpResult;
import com.congda.jianxin.mvp.model.entity.IMImageViewBean;
import com.congda.jianxin.mvp.model.entity.IMUpdataFileBean;
import com.congda.jianxin.mvp.model.entity.VersonBeanData;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;


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
public interface HomeContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void setChooseData(String url);
        void setChooseFileData(String url);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

        Observable<IMHttpResult<IMImageViewBean>> getUpdataPicture(String s);

        Observable<IMUpdataFileBean> getUpdataPictureFile(List<MultipartBody.Part> path);
    }
}
