package com.example.mango_;

public class ContentsModel {
    private String url;
    private String titleImageUrl;
    private String titleText;

    public ContentsModel(String url, String titleImageUrl, String titleText) {
        this.url = url;
        this.titleImageUrl = titleImageUrl;
        this.titleText = titleText;
    }

    public String getUrl() {
        return url;
    }

    public String getTitleImageUrl() {
        return titleImageUrl;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitleImageUrl(String titleImageUrl) {
        this.titleImageUrl = titleImageUrl;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }
}
