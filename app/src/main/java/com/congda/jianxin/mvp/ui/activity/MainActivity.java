package com.congda.jianxin.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.congda.baselibrary.imutils.IMStatusBarUtil;
import com.congda.baselibrary.widget.ViewPagerSlide;
import com.congda.jianxin.R;
import com.congda.jianxin.app.adapter.MyViewPagerAdapter;
import com.congda.jianxin.app.utils.glide.IMImageLoadUtil;
import com.congda.jianxin.di.component.DaggerMainComponent;
import com.congda.jianxin.mvp.contract.MainContract;
import com.congda.jianxin.mvp.presenter.MainPresenter;
import com.congda.jianxin.mvp.ui.IMBaseActivity;
import com.congda.jianxin.mvp.ui.fragment.FindFragment;
import com.congda.jianxin.mvp.ui.fragment.HomeFragment;
import com.jess.arms.di.component.AppComponent;
import com.zhihu.matisse.Matisse;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import butterknife.BindView;

import static com.congda.jianxin.mvp.ui.fragment.HomeFragment.REQUEST_CODE_CHOOSE;
import static com.congda.jianxin.mvp.ui.fragment.HomeFragment.REQUEST_CODE_CHOOSED;


public class MainActivity extends IMBaseActivity<MainPresenter> implements MainContract.View, View.OnClickListener, ViewPager.OnPageChangeListener {
    @BindView(R.id.viewpager)
    ViewPagerSlide mViewPager;

    private int mLastIndex;
    public MyViewPagerAdapter mAdapter;
    public CheckedTextView[] mCheckedTextViews;
    public List<Fragment> mFragments = new ArrayList<>();
    private HomeFragment homeFragment;
    private  List<String>  mSelected;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initStatusBar() {
        IMStatusBarUtil.setTranslucentForImageViewInFragment(this,0, null);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mViewPager.setCanScrollble(true);
        setSwipeBackEnable(false);
        homeFragment = HomeFragment.newInstance();
        mFragments.add(homeFragment);
        mFragments.add(FindFragment.newInstance());
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(FindFragment.newInstance());
        mAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(this);
        initTabs();
    }
    private void initTabs() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.tabs);
        mCheckedTextViews = new CheckedTextView[layout.getChildCount()];
        for (int i = 0; i < layout.getChildCount(); i++) {
            mCheckedTextViews[i] = (CheckedTextView) layout.getChildAt(i);
            mCheckedTextViews[i].setOnClickListener(this);
        }
    }
    @Override
    public void onPageScrollStateChanged(int arg0) {

    }
    @Override
    public void onPageScrolled(int position, float offset, int offsetPixels) {

    }
    @Override
    public void onPageSelected(int position) {
        if(position == mLastIndex) {
            return;
        }
        mCheckedTextViews[position].setChecked(true);
        mCheckedTextViews[mLastIndex].setChecked(false);
        mLastIndex = position;
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < mCheckedTextViews.length; i++) {
            if(v == mCheckedTextViews[i]){
                if(i == mLastIndex){
                    break;
                }
                mCheckedTextViews[i].setChecked(true);
                mCheckedTextViews[mLastIndex].setChecked(false);
                mLastIndex = i;
                mViewPager.setCurrentItem(i, false);
                break;
            }
        }
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {   //选择图片成功之后上传
            mSelected = Matisse.obtainPathResult(data);
            if (mSelected == null && mSelected.size() == 0) {
                return;
            }
            homeFragment.upDataChooseData(mSelected);
        }
        if (requestCode == REQUEST_CODE_CHOOSED && resultCode == RESULT_OK) {   //选择图片成功之后上传
            mSelected = Matisse.obtainPathResult(data);
            if (mSelected == null && mSelected.size() == 0) {
                return;
            }
            homeFragment.upDataChooseFileData(mSelected);
        }
    }
}
