package com.mkyong.components;

import com.mkyong.utils.TimeUtil;

import java.text.*;
import java.util.Date;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mengka
 * @version 2021/5/7
 * @since
 */
public class MyDateFormat extends DateFormat {

    private DateFormat dateFormat;

    private SimpleDateFormat format1 = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

    private static Function<String, Integer> timeFunc = x1 -> {
        Pattern pattern = Pattern.compile("(\\d{4}-\\d{2}-\\d{2}) *(\\d{2}:\\d{2}:\\d{2})$");
        Matcher matcher = pattern.matcher(x1);
        if (matcher.matches()) {
            return 1;
        }else{
            Pattern pattern2 = Pattern.compile("(\\d{4}-\\d{2}-\\d{2})$");
            Matcher matcher2 = pattern2.matcher(x1);
            if(matcher2.matches()) {
                return 2;
            }
        }
        return -1;
    };

    public MyDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return dateFormat.format(date, toAppendTo, fieldPosition);
    }


    @Override
    public Date parse(String source, ParsePosition pos) {
        Date date = null;
        try {
            date = format1.parse(source, pos);
        } catch (Exception e) {

            date = dateFormat.parse(source, pos);
        }
        return date;
    }

    /**
     *  String ==>> Date日期格式
     *
     * @param source
     * @return
     * @throws ParseException
     */
    // 主要还是装饰这个方法
    @Override
    public Date parse(String source) throws ParseException {

        Date date = null;

        try {
            // 先按我的规则来
            int type = timeFunc.apply(source);
            switch (type){
                case 1:
                    date = TimeUtil.toDate(source,TimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
                    break;
                case 2:
                    date = TimeUtil.toDate(source,TimeUtil.FORMAT_YYYYMMDD2);
                    break;
                default:
                    break;
            }

        } catch (Exception e) {

            // 不行，那就按原先的规则吧
            date = dateFormat.parse(source);
        }

        return date;
    }

    // 这里装饰clone方法的原因是因为clone方法在jackson中也有用到
    @Override
    public Object clone() {
        Object format = dateFormat.clone();
        return new MyDateFormat((DateFormat) format);
    }
}