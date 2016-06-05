package com.owo.mwb.service.display;

import com.owo.mwb.service.display.entity.DisplayItem;

import java.util.List;

import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by wangli on 16-6-5.
 */
public interface DisplayService {
    @GET("/service/display/get_items")
    void getItems(int start, int count, Callback<List<DisplayItem>> callback);

    @GET("/service/display/like")
    void like(String id, boolean like, Callback<Boolean> callback);
}
