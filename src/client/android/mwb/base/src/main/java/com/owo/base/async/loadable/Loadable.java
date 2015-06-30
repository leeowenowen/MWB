package com.owo.java_base.async.loadable;

/**
 * Created by wangli on 15-7-1.
 */
public class Loadable {
    public static enum State {
        NONE,//
        LOADING,//
        LOADED_FAILED,//
        LOADED_SUCCESS,//
    }

    private State mState = State.NONE;

    public void setState(State state) {
        mState = state;
    }

    public State getState() {
        return mState;
    }

    public boolean isLoaded() {
        return mState == State.LOADED_SUCCESS;
    }
}
