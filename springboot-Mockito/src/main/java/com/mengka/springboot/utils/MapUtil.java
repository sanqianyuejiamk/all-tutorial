package com.mengka.springboot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

/**
 * @Author wujie
 * @Class MapUtil
 * @Description
 * @Date 2020/11/26 15:30
 */
public class MapUtil {
    private final static Logger logger = LoggerFactory.getLogger(MapUtil.class);

    public static <T> T getMap(Map data, String key) {
        Object value = data.get(key);
        if (value != null) {
            return (T) value;
        }
        return null;
    }

    public static <T> T getMap(Map data, String key, Class<T> clazz, T defaultValue) {
        Object value = data.get(key);
        try {
            if (value != null) {
                return clazz.cast(value);
            }
        } catch (Exception e) {
            logger.info("转化出现错误 ", e);
        }
        return defaultValue;
    }

}