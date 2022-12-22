package com.example.mango_;

public class ContentsModel {
    private String url;
    private String imageUrl;
    private String titleText;

    public ContentsModel(String url, String imageUrl, String titleText) {
        this.url = url;
        this.imageUrl = imageUrl;
        this.titleText = titleText;
    }

    public ContentsModel() {

    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitleText() {
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

    public void test() {
    }
}
