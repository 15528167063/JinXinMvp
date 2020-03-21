package com.congda.jianxin.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.congda.jianxin.R;
import com.congda.jianxin.app.utils.glide.IMGifSizeFilter;
import com.congda.jianxin.app.utils.glide.IMGlide4Engine;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.listener.OnCheckedListener;
import com.zhihu.matisse.listener.OnSelectedListener;

import java.util.List;

public class IMChooseUtils {

    /**
     * 选择图片
     */
    public static  void choosePhotoForResult(Context context,int requestCode,int maxnumber){
        Matisse.from((Activity) context)
                .choose(MimeType.ofImage(), false)
                .showSingleMediaType(true)
                .countable(true)
                .capture(true)
                .captureStrategy(new CaptureStrategy(true, "com.example.ycz.basemodule.fileprovider","test"))
                .maxSelectable(maxnumber)
                .addFilter(new IMGifSizeFilter(320, 320, 10 * Filter.K * Filter.K))
                .gridExpectedSize(context.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new IMGlide4Engine())    // for glide-V4
                .setOnSelectedListener(new OnSelectedListener() {
                    @Override
                    public void onSelected(@NonNull List<Uri> uriList, @NonNull List<String> pathList) {
                        Log.e("onSelected", "onSelected: pathList=" + pathList);

                    }
                })
                .originalEnable(true)
                .maxOriginalSize(10)
                .autoHideToolbarOnSingleTap(true)
                .setOnCheckedListener(new OnCheckedListener() {
                    @Override
                    public void onCheck(boolean isChecked) {
                        // DO SOMETHING IMMEDIATELY HERE
                        Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                    }
                })
                .forResult(requestCode);
    }

    /**
     * 选择视频
     */
    public static void ChooseVideoForResult(Context context,int requestCode) {
        Matisse.from((Activity)context)
                .choose(MimeType.ofVideo(), false)
                .showSingleMediaType(true)
                .countable(true)
                .capture(false)
                .captureStrategy(new CaptureStrategy(true, "com.example.ycz.basemodule.fileprovider", "test"))
                .maxSelectable(1)
                .addFilter(new IMGifSizeFilter(320, 320, 15 * Filter.K * Filter.K))
                .gridExpectedSize(context.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new IMGlide4Engine())    // for glide-V4
                .setOnSelectedListener(new OnSelectedListener() {
                    @Override
                    public void onSelected(@NonNull List<Uri> uriList, @NonNull List<String> pathList) {
                        Log.e("onSelected", "onSelected: pathList=" + pathList);

                    }
                })
                .originalEnable(true)
                .maxOriginalSize(10)
                .autoHideToolbarOnSingleTap(true)
                .setOnCheckedListener(new OnCheckedListener() {
                    @Override
                    public void onCheck(boolean isChecked) {
                        // DO SOMETHING IMMEDIATELY HERE
                        Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                    }
                })
                .forResult(requestCode);
    }
}
