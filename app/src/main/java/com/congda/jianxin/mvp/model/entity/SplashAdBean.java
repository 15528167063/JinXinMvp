package com.congda.jianxin.mvp.model.entity;

public class SplashAdBean {
    private String adsId;
    private String adsImgUrl;//广告图片地址
    private String adsName;
    private int autoTime;
    private String url;  //点击跳转地址
    private String message;
    private String locationCode;

    public String getAdsId() {
        return adsId;
    }

    public void setAdsId(String adsId) {
        this.adsId = adsId;
    }

    public String getAdsImgUrl() {
        return adsImgUrl;
    }

    public void setAdsImgUrl(String adsImgUrl) {
        this.adsImgUrl = adsImgUrl;
    }

    public String getAdsName() {
        return adsName;
    }

    public void setAdsName(String adsName) {
        this.adsName = adsName;
    }

    public int getAutoTime() {
        return autoTime;
    }

    public void setAutoTime(int autoTime) {
        this.autoTime = autoTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }
}
