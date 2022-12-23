package com.example.mango_;

import android.util.Log;

public class ContentsModel {
    private String url;
    private String imageUrl;
    private String titleText;

    public ContentsModel(String url, String imageUrl, String titleText) {
        Log.i("app_test","ContentsModel.class, " + "ContentsModel Constructor");
        this.url = url;
        this.imageUrl = imageUrl;
        this.titleText = titleText;
    }

    public ContentsModel() {
        Log.i("app_test","ContentsModel.class, " + "No args Constructor");
    }

    public String getUrl() {
        Log.i("app_test","ContentsModel.class, " + "getUrl");
        return url;
    }

    public String getImageUrl() {
        Log.i("app_test","ContentsModel.class, " + "getImageUrl");
        return imageUrl;
    }

    public String getTitleText() {
        Log.i("app_test","ContentsModel.class, " + "getTitleText");
        return titleText;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

}
