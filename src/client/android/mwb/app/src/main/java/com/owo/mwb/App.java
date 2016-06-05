package com.owo.mwb;

import android.app.Application;
import android.util.Log;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by wangli on 16-6-5.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler());
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).memoryCacheSize(1024 * 1024 * 4).build();
        ImageLoader.getInstance().init(config);
    }

    class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        public void uncaughtException(Thread t, Throwable e) {
            Log.e("Master", "Uncaught exception met from thread " + t.getName(), e);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
