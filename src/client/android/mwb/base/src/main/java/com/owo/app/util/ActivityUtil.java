package com.owo.app.util;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by wangli on 16-6-11.
 */
public class ActivityUtil {
    public static void setFullScreen(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);

    }
}
