package com.example.mango_;

public class ContentsModel {
    private String url = "";
    private String titleImageUrl = "";
    private String titleText = "";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitleImageUrl() {
        return titleImageUrl;
    }

    public void setTitleImageUrl(String titleImageUrl) {
        this.titleImageUrl = titleImageUrl;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public ContentsModel(String url, String titleImageUrl, String titleText) {
        this.url = url;
        this.titleImageUrl = titleImageUrl;
        this.titleText = titleText;
    }


}
