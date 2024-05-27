package com.mengka.springboot.utils;

import org.apache.commons.lang.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author huangyy
 * @description
 * @data 2016/12/08.
 */
public class TimeUtil {
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String yyyyMMddHH = "yyyyMMddHH";
    public static final String yyyyMMdd = "yyyyMMdd";
    public static final String yyyyMM = "yyyyMM";

    public static String toDate(Date dt, String sFmt) {
        if(null != dt && !StringUtils.isBlank(sFmt)) {
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
        if(!StringUtils.isBlank(sDate) && !StringUtils.isBlank(sFmt)) {
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

    public static Date dayBefore(Date date,int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, days);
        return calendar.getTime();
    }

    public static Date dayOfMonthFirst(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        return calendar.getTime();
    }

    public static Date dayOfMonthLast(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

}
