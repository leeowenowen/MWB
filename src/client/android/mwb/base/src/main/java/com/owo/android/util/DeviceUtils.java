package com.owo.android.util;

import android.content.Context;
import android.os.PowerManager;

import com.owo.android.util.ContextManager;

public class DeviceUtils {
    private static PowerManager.WakeLock screenOnLock = null;

    private static void Destroy() {
        UnlockScreenOn();
    }

    public static void LockScreenOn() {
        if (null == screenOnLock) {
            PowerManager pm = (PowerManager) ContextManager.systemService(Context.POWER_SERVICE);
            if (null == pm) {
                return;
            }

            screenOnLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "screen on lock");
            screenOnLock.setReferenceCounted(true);
        }

        if (null != screenOnLock) {
            screenOnLock.acquire();
        }
    }

    public static void UnlockScreenOn() {
        if (null != screenOnLock) {
            if (screenOnLock.isHeld()) {
                screenOnLock.release();
            }
        }
    }
}
