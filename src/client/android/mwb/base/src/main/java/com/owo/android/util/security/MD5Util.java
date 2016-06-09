package com.owo.android.util.security;

import com.owo.android.util.TextUtil;

import java.security.MessageDigest;

public class MD5Util {
    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
                'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            return TextUtil.byteToHexStr(md);
        } catch (Exception e) {
            e.printStackTrace();
            //special handler here
            return "" + System.currentTimeMillis();
        }
    }
}
