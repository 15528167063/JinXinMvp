package com.congda.jianxin.di.component.fragment;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.congda.jianxin.di.module.fragment.FindModule;
import com.congda.jianxin.mvp.contract.fragment.FindContract;

import com.jess.arms.di.scope.FragmentScope;
import com.congda.jianxin.mvp.ui.fragment.FindFragment;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 03/20/2020 17:42
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
@Component(modules = FindModule.class, dependencies = AppComponent.class)
public interface FindComponent {
    void inject(FindFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        FindComponent.Builder view(FindContract.View view);

        FindComponent.Builder appComponent(AppComponent appComponent);

        FindComponent build();
    }
}