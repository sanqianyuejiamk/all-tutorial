//package com.mkyong.utils;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//import java.util.function.BiFunction;
//import java.util.function.Function;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * Date类型转换器
// *
// * 1）UTC日期即世界时，即格林尼治平太阳时  格式：yyyy-MM-dd'T'HH:mm:ss
// * 2）UTC就是世界标准时间，与北京时间相差八个时区。所以只要将UTC时间转化成一定格式的时间，再在此基础上加上8个小时就得到北京时间了。
// *
// * @author mengka
// * @version 2021/5/7
// * @since
// */
//@Component
//public class DateConverter implements Converter<String, Date> {
//
//    @Override
//    public Date convert(String source) {
//
//        return TimeUtil.toDate(source,TimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
//
//        /**
//         *  日期函数：
//         *    支持"yyyy-MM-dd HH:mm:ss" 和 "yyyy-MM-dd"
//         */
//        Function<String, Integer> timeFunc = x1 -> {
//            Pattern pattern = Pattern.compile("(\\d{4}-\\d{2}-\\d{2}) *(\\d{2}:\\d{2}:\\d{2})$");
//            Matcher matcher = pattern.matcher(x1);
//            if (matcher.matches()) {
//                return 1;
//            }else{
//                Pattern pattern2 = Pattern.compile("(\\d{4}-\\d{2}-\\d{2})$");
//                Matcher matcher2 = pattern2.matcher(x1);
//                if(matcher2.matches()) {
//                    return 2;
//                }
//            }
//            return -1;
//        };
//
//        if (source != null && !"".equals(source)) {
//            int type = timeFunc.apply(source);
//            switch (type){
//                case 1:
//                    return LocalDateTime.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//                case 2:
//                    LocalDate tmpDate = LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//                    return LocalDateTime.of(tmpDate,LocalTime.parse("00:00:00"));
//                default:
//                    return null;
//            }
//        }
//        return null;
//    }
//}