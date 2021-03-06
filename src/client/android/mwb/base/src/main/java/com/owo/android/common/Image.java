package com.owo.android.common;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;


/**
 * Created by wangli on 16-6-5.
 */
public class Image extends com.owo.base.async.loadable.Loadable {

    private String url;

    @Expose(serialize = false, deserialize = false)
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
