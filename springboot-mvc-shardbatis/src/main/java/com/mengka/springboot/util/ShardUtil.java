package com.mengka.springboot.util;

/**
 * @author huangyy
 * @description
 * @date 2017/04/30.
 */
public class ShardUtil {

    public ShardUtil() {
    }

    public static String getShardSuffix(String fieldValue, int tableNum) {
        return tableNum == 1?"":(fieldValue != null && !fieldValue.equals("")?String.format("_%03d", new Object[]{Integer.valueOf(Math.abs(fieldValue.hashCode()) % tableNum)}):"");
    }
}
