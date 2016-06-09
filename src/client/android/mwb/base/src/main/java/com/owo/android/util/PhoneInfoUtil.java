package com.owo.android.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PhoneInfoUtil {

    private static final String CLASS_NAME = PhoneInfoUtil.class.getSimpleName();

    public static String getMobileNum(Context ctx) {
        String phoneNum = null;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) ctx
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String mobileNumber = telephonyManager.getLine1Number();
            if (mobileNumber != null && mobileNumber.length() > 11) {
                phoneNum = mobileNumber
                        .substring(mobileNumber.length() - 11, mobileNumber.length());
            }
        } catch (Exception e) {
        }

        return TextUtil.ensureNotNull(phoneNum);
    }

    /**
     * A 64-bit number (as a hex string) that is randomly generated on the
     * device's first boot and should remain constant for the lifetime of the
     * device. (The value may change if a factory reset is performed on the
     * device.)
     */
    public static String androidId() {
        return Secure.getString(ContextManager.contentResolver(), Secure.ANDROID_ID);
    }

    public static String imei() {
        String imei = null;
        try {
            imei = ((TelephonyManager) ContextManager.systemService(Context.TELEPHONY_SERVICE))//
                    .getDeviceId();
        } catch (Exception e) {
        }
        return TextUtil.ensureNotNull(imei);
    }

    public static String imsi() {
        String imsi = null;
        try {
            imsi = ((TelephonyManager) ContextManager.systemService(Context.TELEPHONY_SERVICE))//
                    .getSubscriberId();
        } catch (Exception e) {
        }
        return TextUtil.ensureNotNull(imsi);
    }

    public static String wifiMac() {
        String mac = null;
        try {
            mac = ((WifiManager) ContextManager.systemService(Context.WIFI_SERVICE))//
                    .getConnectionInfo().getMacAddress();
        } catch (Exception e) {
        }

        return TextUtil.ensureNotNull(mac);
    }

    public static boolean isSimAvaliable(Context ctx) {
        TelephonyManager telephonyManager = (TelephonyManager) ctx
                .getSystemService(Context.TELEPHONY_SERVICE);
        int simState = telephonyManager.getSimState();
        return (simState == TelephonyManager.SIM_STATE_READY) ? true : false;
    }

    public static String getAndroidId(Context ctx) {
        String androidId = Secure.getString(ctx.getContentResolver(), Secure.ANDROID_ID);
        return androidId == null ? "" : androidId;
    }

    /**
     * 获取安装标识 2.3以上获取安装时间 ，2.3以下获取data文件夹下的lib目录的iNode值
     *
     * @param ctx
     * @return
     */
    @SuppressLint("NewApi")
    public static String getInstallId(Context ctx) {
        String installId = "";
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD) {
            try {
                installId = String.valueOf(ctx.getPackageManager().getPackageInfo(
                        ctx.getPackageName(), 0).firstInstallTime);
            } catch (NameNotFoundException e) {
            }
        } else {
            installId = String.valueOf(PhoneInfoUtil.getiNode(ctx.getFilesDir().getParent()
                    + File.separator + "lib"));
        }

        return installId;
    }

    /**
     * 获取文件或文件夹的inode
     *
     * @param path
     * @return
     */
    public static int getiNode(String path) {

        int inode = -1;

        try {
            Class<?> fileUtilClass = Class.forName("android.os.FileUtils");
            Class<?> fileStatusClass = Class.forName("android.os.FileUtils$FileStatus");

            Method getFileStatus = fileUtilClass.getMethod("getFileStatus", String.class,
                    fileStatusClass);

            Object status = fileStatusClass.newInstance();
            boolean invokeSuc = (Boolean) getFileStatus.invoke(fileUtilClass.newInstance(), path,
                    status);

            if (invokeSuc) {
                Field field = fileStatusClass.getField("ino");

                inode = field.getInt(status);
            }
        } catch (Exception e) {
        }

        return inode;
    }

    /**
     * 获取手机运营商
     *
     * @return
     */
    public static String getOperator(Context ctx) {
        String type = "";
        if (isSimAvaliable(ctx)) {
            TelephonyManager telephonyManager = (TelephonyManager) ctx
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String operator = telephonyManager.getSimOperator();
            if (operator != null) {
                type = operator;
            }
        }

        return type;
    }
}