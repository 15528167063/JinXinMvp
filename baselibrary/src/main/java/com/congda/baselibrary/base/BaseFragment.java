package com.congda.baselibrary.base;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.congda.baselibrary.loading.ShowLoadiongUtils;


public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=initView();
        iniData();
        return view;
    }
    public abstract void iniData();

    public abstract View initView();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    /**
     * 通用Dialog弹窗
     */
    public void showDialog(String title, String message, DialogInterface.OnClickListener listener ){
        ShowLoadiongUtils.showDialog(getActivity(),title,message,listener);
    }
    /**
     * 显示加载界面loading(第一种自定义)
     */
    public void showLoadingDialog() {
        ShowLoadiongUtils.showLoadingDialogTypeOne(getActivity());
    }
    public void dismissLoadingDialog() {
        ShowLoadiongUtils.dismissLoadingDialogTypeOne();
    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }
}
