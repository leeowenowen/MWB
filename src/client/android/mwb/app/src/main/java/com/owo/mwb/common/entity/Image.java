package com.owo.mwb.common.entity;

import android.graphics.Bitmap;

/**
 * Created by wangli on 16-6-5.
 */
public class Image {

    private String url;
    private Bitmap bmp;

    public Image(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }

}
