package com.congda.baselibrary.base;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.congda.baselibrary.R;
import com.congda.baselibrary.imutils.IMLogUtil;
import com.congda.baselibrary.imutils.IMStatusBarUtil;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author jinxin
 * 剑之所指，心之所向
 * @date 2019/8/4
 */
public class BaseActivity extends JxSwipeBackActivity implements EasyPermissions.PermissionCallbacks{
    String[] perms = { Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStatusBar();
        requestPermissions();
    }
    /**
     * 默认状态栏白底黑字
     */
    public void initStatusBar() {
        IMStatusBarUtil.setColor(this, getResources().getColor(R.color.white),0);
        IMStatusBarUtil.setLightMode(this);
    }

    /**
     * 动态添加权限
     */
    private void requestPermissions() {
        if (EasyPermissions.hasPermissions(this, perms)) {
            //todo 已经获取了权限
            IMLogUtil.d("tag", "BaseActivity " +"(requestPermissions) " + true , 45, "perms = " + perms);
        } else {
            //没有获取了权限，申请权限
            EasyPermissions.requestPermissions(this, "为了优化您的使用体验，请打开下列必要的权限", 0, perms);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}
