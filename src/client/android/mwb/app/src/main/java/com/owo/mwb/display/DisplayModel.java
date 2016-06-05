package com.owo.mwb.display;

import com.google.common.collect.ArrayTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by wangli on 16-6-5.
 */
public class DisplayModel extends ArrayList<DisplayItem> {
    private static DisplayModel instance = new DisplayModel();
    private Observable mObservable = new Observable();

    public static DisplayModel instance() {
        return instance;
    }

    public Observable asObservable() {
        return mObservable;
    }
}
