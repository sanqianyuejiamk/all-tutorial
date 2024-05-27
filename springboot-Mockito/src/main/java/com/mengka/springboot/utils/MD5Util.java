package com.mengka.springboot.utils;

import java.security.MessageDigest;
import java.util.Locale;

/**
 * @Author wujie
 * @Class MD5Util
 * @Description
 * @Date 2020/12/3 9:58
 */
public class MD5Util {

    public static String MD532Low(String source) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes());
            byte bytes[] = md.digest();
            for (int offset = 0; offset < bytes.length; offset++) {
                int i = bytes[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    public static String MD532Up(String source) {
        return MD532Low(source).toUpperCase(Locale.US);
    }

    public static String MD516Up(String source) {
        return MD532Low(source).substring(8, 24).toUpperCase(Locale.US);
    }

    public static String MD516Low(String source) {
        return MD532Low(source).substring(8, 24);
    }


}