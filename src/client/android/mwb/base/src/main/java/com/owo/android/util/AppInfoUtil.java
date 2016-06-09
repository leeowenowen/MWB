package com.owo.android.util;

import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;

import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;


public class AppInfoUtil {
    private static String packageName;

    public static String packageName() {
        if (packageName == null) {
            packageName = ContextManager.appContext().getPackageName();
        }
        return packageName;
    }

    private static String versionName;

    public static String versionName() {
        if (versionName == null) {
            try {
                versionName = ContextManager.packageManager().getPackageInfo(packageName(), 0).versionName;
            } catch (NameNotFoundException e) {
            }
            versionName = TextUtil.ensureNotNull(versionName);
        }
        return versionName;
    }

    public static final int INVALID_VERSION_CODE = -1;
    private static int versionCode = INVALID_VERSION_CODE;

    public static int versionCode() {
        if (versionCode == INVALID_VERSION_CODE) {
            try {
                versionCode = ContextManager.packageManager().getPackageInfo(packageName(), 0).versionCode;
            } catch (Exception e) {
            }
        }
        return versionCode;
    }

    public static String processName() {
        return ContextManager.appInfo().processName;
    }

    public static int uid() {
        return ContextManager.appInfo().uid;
    }

    public static String signature() {
        String ret = "";
        try {
            Signature[] sigs = ContextManager.packageManager().getPackageInfo(packageName(),//
                    PackageManager.GET_SIGNATURES).signatures;

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

            X509Certificate X509Cert = (X509Certificate) certificateFactory.generateCertificate(//
                    new ByteArrayInputStream(sigs[0].toByteArray()));

            ret = TextUtil.byteToHexStr(md5.digest(X509Cert.getSignature()));
        } catch (NameNotFoundException | NoSuchAlgorithmException | CertificateException e) {

        }

        return ret;
    }
}