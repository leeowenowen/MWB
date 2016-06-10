package com.owo.mwb.user;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by wangli on 16-6-5.
 */
public class UserModel extends ArrayList<User> {
    private static UserModel instance = new UserModel();
    private Observable mObservable = new Observable();

    public static UserModel instance() {
        return instance;
    }

    public Observable asObservable() {
        return mObservable;
    }

    private UserModel() {

    }
}
