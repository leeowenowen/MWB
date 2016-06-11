package com.owo.mwb.user;

import com.owo.mwb.display.DisplayItem;

import java.util.List;

import retrofit2.Callback;
import retrofit2.http.GET;

/**
 * Created by wangli on 16-6-5.
 */
public interface UserService {
    @GET("/service/user/register")
    void register(User user, Callback<User> callback);

    @GET("/service/user/login")
    void login(User user, Callback<User> callback);

    @GET("/service/user/logout")
    void logout(User user, Callback<User> callback);

    @GET("/service/user/get")
    void getUserInfo(int id, Callback<List<User>> callback);

}
