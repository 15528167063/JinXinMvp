package com.congda.jianxin.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.congda.jianxin.R;
import com.congda.jianxin.app.utils.IMChooseUtils;
import com.congda.jianxin.app.utils.glide.IMImageLoadUtil;
import com.congda.jianxin.di.component.fragment.DaggerHomeComponent;
import com.congda.jianxin.mvp.contract.fragment.HomeContract;
import com.congda.jianxin.mvp.presenter.fragment.HomePresenter;
import com.congda.jianxin.mvp.ui.IMBaseFragment;
import com.jess.arms.di.component.AppComponent;

import java.util.List;

import butterknife.BindView;


public class HomeFragment extends IMBaseFragment<HomePresenter> implements HomeContract.View, View.OnClickListener {
    @BindView(R.id.iv)
    ImageView imageView1;
    @BindView(R.id.iv1)
    ImageView imageView2;
    private  List<String>  mSelected;
    public static  final  int REQUEST_CODE_CHOOSE=10001;
    public static final int REQUEST_CODE_CHOOSED =10002 ;
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerHomeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv:
                IMChooseUtils.choosePhotoForResult(getActivity(),REQUEST_CODE_CHOOSE,1);
                break;
            case R.id.iv1:
                IMChooseUtils.choosePhotoForResult(getActivity(),REQUEST_CODE_CHOOSED,3);
                break;
        }
    }
    public void upDataChooseData(List<String> mSelected) {
        this.mSelected=mSelected;
        mPresenter.updataPickture(mSelected);
    }
    public void upDataChooseFileData(List<String> mSelected) {
        this.mSelected=mSelected;
        mPresenter.updataPicktureFile(mSelected);
    }

    public void setChooseData(String url) {
        IMImageLoadUtil.CommonImageRoundLoad(getActivity(),url,imageView1);
    }
}
