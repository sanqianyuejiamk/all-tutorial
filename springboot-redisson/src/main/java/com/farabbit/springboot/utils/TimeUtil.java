package com.farabbit.springboot.utils;

import org.apache.commons.lang.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static final String format_1 = "yyyy-MM-dd HH:mm:ss";
    public static final String format_2 = "yyyyMMddHHmmss";
    public static final String format_3 = "yyyy-MM-dd";
    public static final String format_4 = "yyyy年MM月dd日";

    public TimeUtil() {
    }

    public static String toDate(Date dt, String sFmt) {
        if (null != dt && !StringUtils.isBlank(sFmt)) {
            SimpleDateFormat sdfFrom = null;
            String sRet = null;

            Object var5;
            try {
                sdfFrom = new SimpleDateFormat(sFmt);
                sRet = sdfFrom.format(dt).toString();
                return sRet;
            } catch (Exception var9) {
                var5 = null;
            } finally {
                sdfFrom = null;
            }

            return (String)var5;
        } else {
            return null;
        }
    }

    public static Date toDate(String sDate, String sFmt) {
        if (!StringUtils.isBlank(sDate) && !StringUtils.isBlank(sFmt)) {
            SimpleDateFormat sdfFrom = null;
            Date dt = null;

            Object var5;
            try {
                sdfFrom = new SimpleDateFormat(sFmt);
                dt = sdfFrom.parse(sDate);
                return dt;
            } catch (Exception var9) {
                var5 = null;
            } finally {
                sdfFrom = null;
            }

            return (Date)var5;
        } else {
            return null;
        }
    }
}