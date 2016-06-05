package com.owo.mwb.util;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by wangli on 16-6-5.
 */
public class RetrofitUtil {
    private static Retrofit instance = new Retrofit.Builder()
            .baseUrl("https://com.owo.api/")
            .client(new OkHttpClient())
            .build();

    public static <T> T create(Class<T> cls) {
        return instance.create(cls);
    }
}
