/**
 *
 * Copyright © 2010 杭州邦盛金融信息技术有限公司 版权所有
 *
 */

package com.mengka.springboot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类 时间:2015年7月27日 上午10:59:38 <br/>
 * 
 * @author cpf
 * @version
 */
public class DateUtil {
    public final static String FORMAT_DATE = "yyyy-MM-dd";
    public final static String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

    public final static String TYPE_DATE = "date";
    public final static String TYPE_DATETIME = "datetime";
    
    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
    
    public static SimpleDateFormat YMD = new SimpleDateFormat("yyyyMMdd");
    public static SimpleDateFormat SDF_YMD = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat SDF_YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public final static String YYYY_MM_DDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * 返回yyyy-MM-dd HH:mm:ss格式的时间字符串
     * 
     * @param date
     * @return
     */
    public static String getDateOfSDF_YMDHMS(Date date) {

        return SDF_YMDHMS.format(date);
    }

    /**
     * 返回yyyy-MM-dd格式的时间字符串
     * 
     * @param date
     * @return
     */
    public static String getDateOfYMD(Date date) {

        return SDF_YMD.format(date);
    }

    /**
     * 返回yyyyMMdd格式的时间字符串
     * 
     * @param date
     * @return
     */
    public static String getDateYMD(Date date) {
        return YMD.format(date);
    }

    /**
     * 返回自定义format格式的字符串
     * 
     * @param date
     * @param format
     * @return
     */
    public static String getDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date dateStrToDate(String dateStr, String format) throws ParseException {
        if (dateStr!=null && !dateStr.trim().equals("")) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr);
        }
        return null;
    }

    /**
     * yyyyMMddHHmmss --> yyyy-MM-dd HH:mm:ss
     * 
     * @param dateStr
     * @return
     */
    public static String dateStrToStr(String dateStr) {
        try {
            Date date = dateStrToDate(dateStr, YYYYMMDDHHMMSS);
            if (null != date) {
                return getDateOfSDF_YMDHMS(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间格式进行转化
     * 
     * @param date
     * @return
     */
    public static Long getTime(String date) {
        Long times = null;
        try {
            Date dd = YMD.parse(date);
            times = dd.getTime();
        } catch (Exception e) {
            times = null;
        }
        return times;
    }

    @SuppressWarnings("rawtypes")
    private static ThreadLocal threadLocal = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    @SuppressWarnings("rawtypes")
    private static ThreadLocal threadLocalYYYY_MM_DDHHMMSS = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static DateFormat getDateYYYY_MM_DDHHMMSSFormat() {
        return (DateFormat) threadLocalYYYY_MM_DDHHMMSS.get();
    }

    public static DateFormat getDateFormat() {
        return (DateFormat) threadLocal.get();
    }

    /**
     * 时间格式进行转化 线程安全
     * 
     * @param date
     * @return
     */
    public static Long getThreadLocalTime(String date) {
        Long times = null;
        try {
            Date dd = getDateFormat().parse(date);
            times = dd.getTime();
        } catch (Exception e) {
            times = null;
        }
        return times;
    }

    /**
     * 时间格式进行转化 线程安全
     * 
     * @param date
     * @return
     */
    public static Date getThreadLocalDate(String date) {
        Date times = null;
        try {
            times = getDateFormat().parse(date);
        } catch (Exception e) {
            times = null;
        }
        return times;
    }

    /**
     * 获取下一天的日期
     * 
     * @param date
     * @return
     */
    public static String getNextDay(Date date) {
        return YMD.format(new Date(date.getTime() + 24 * 3600 * 1000));
    }

    /**
     * 获取下一天的日期
     * 
     * @param date
     * @param format
     *            日期格式
     * @return
     */
    public static String getNextDay(Date date, String format) {
        return new SimpleDateFormat(format).format(new Date(date.getTime() + 24 * 3600 * 1000));
    }

    /**
     * 获取前一天的日期
     * 
     * @param date
     * @return
     */
    public static String getPerDay(Date date) {
        return YMD.format(new Date(date.getTime() - 24 * 3600 * 1000));
    }

    /**
     * 获取时间格式为xxxx年xx月xx日
     * 
     * @param date
     * @return
     */
    public static String getYMD(Date date) {
        if (null == date) {
            date = new Date();
        }
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "年" + (c.get(Calendar.MONTH) + 1) + "月" + c.get(Calendar.DAY_OF_MONTH) + "日";
    }

    /**
     * 判断是否是时间格式
     * 
     * @param date
     *            时间字符串
     * @param format
     *            时间格式
     * @return
     */
    public static boolean isDateFomat(String date, String format) {
        boolean bool = true;
        try {
            SimpleDateFormat f = new SimpleDateFormat(format);
            f.parse(date);
        } catch (Exception e) {
            bool = false;
            logger.warn(date + " change exception :" + e.getMessage());
        }
        return bool;
    }

    /**
     * 获取当前时间的下一天这时候
     * 
     * @param date
     * @return
     */
    public static Date getNextDayDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 获取month个月之后的时间
     * 
     * @param date
     * @return
     */
    public static Date getMonthDate(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * yyyy-MM-dd HH:mm:ss形式字符串转换为<code>Date</code>类型对象
     * 
     * @param dateTime
     * @return <code>Date</code>类型对象；当转换失败时返回<code>null</code>
     */
    public static Date dateTimeString2Date(String dateTime) {
        try {
            return SDF_YMDHMS.parse(dateTime);
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn(dateTime + " change exception :" + e.getMessage());
            return null;
        }
    }

    /**
     * yyyy-MM-dd形式字符串转换为<code>Date</code>类型对象
     * 
     * @param date
     * @return <code>Date</code>类型对象；当转换失败时返回<code>null</code>
     */
    public static Date dateString2Date(String date) {
        try {
            return SDF_YMD.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn(date + " change exception :" + e.getMessage());
            return null;
        }
    }

    /**
     * 获取当前时间<code>now</code>提前<code>preDay</code>的日期,以 yyyy-MM-dd格式返回的字符串
     * 
     * @param now
     *            当前时间
     * @param preDay
     *            提前多少天
     * @return
     * @throws BaseException
     */
    public static String getPreSomeDay(String now, int preDay) {
        Calendar cal = Calendar.getInstance();
        DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");// 显示时间格式
        try {
            Date date = ft.parse(now);
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, 0 - preDay);
            Date newDate = cal.getTime();
            return ft.format(newDate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 取得当前时间的UTC时间形式。
     * 
     * @return UTC时间毫秒数。
     */
    public static long getUTCTime() {
        Calendar cal = Calendar.getInstance();
        // 时区
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        // 取得夏令时
        int dstOffset = cal.get(Calendar.DST_OFFSET);
        // 扣除差量
        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        return cal.getTimeInMillis();
    }

    /**
     * 今天的开始时间。
     * 
     * @return
     */
    public static Date getBeginTime() {
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 用字符串获得日期
     * 
     * @throws ParseException
     * @dateValue 日期字符串
     * @dateType 格式化的类型,date和datetime
     */
    public static Date getDate(String dateValue, String dateType)
            throws ParseException {
        if (dateValue == null)
            return null;
        if (dateType.equals(TYPE_DATE)) {
            SimpleDateFormat sfdate = new SimpleDateFormat(FORMAT_DATE);
            return sfdate.parse(dateValue);
        } else if (dateType.equals(TYPE_DATETIME)) {
            SimpleDateFormat sftime = new SimpleDateFormat(FORMAT_DATETIME);
            return sftime.parse(dateValue);
        }
        return null;
    }
    
    /**
     * 
     * 用日期获得x月前或者x月后的日期，返回格式化日期
     *
     * @param date 日期
     * @param year 加或者减 月
     * @param dateType 格式化的类型,date和datetime
     * @return 字符串类型日期
     */
    public static Date addMonths(Date date, int months) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        Date targetDate = calendar.getTime();
        return targetDate;
    }
    
    /**
     * 
     * 用日期获得x年前或者x年后的日期，返回格式化日期
     *
     * @param date 日期
     * @param year 加或者减 年
     * @param dateType 格式化的类型,date和datetime
     * @return 字符串类型日期
     */
    public static String addDateByYears(Date date, int year, String dateType) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        Date targetDate = calendar.getTime();
        if (dateType.equals(TYPE_DATE)) {
            SimpleDateFormat sfdate = new SimpleDateFormat(FORMAT_DATE);
            return sfdate.format(targetDate);
        } else if (dateType.equals(TYPE_DATETIME)) {
            SimpleDateFormat sftime = new SimpleDateFormat(FORMAT_DATETIME);
            return sftime.format(targetDate);
        }
        return null;
    }
    
    /**
     * 
     * 格式化日期
     *
     * @author cxw
     * @param date 日期
     * @param dateType dateType 格式化的类型,date和datetime
     * @return
     */
    public static String formateTime(Date date, String dateType) {
        if(date == null) {
            return null;
        }
        if (dateType.equals(TYPE_DATE)) {
            SimpleDateFormat sfdate = new SimpleDateFormat(FORMAT_DATE);
            return sfdate.format(date);
        } else if (dateType.equals(TYPE_DATETIME)) {
            SimpleDateFormat sftime = new SimpleDateFormat(FORMAT_DATETIME);
            return sftime.format(date);
        }
        return null;
    }
}
