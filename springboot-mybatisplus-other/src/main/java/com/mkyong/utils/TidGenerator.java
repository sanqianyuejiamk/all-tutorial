package com.mkyong.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 较短的有序序列生成算法
 * <p>1. 首先使用「类雪花算法」生成一个有序序列</p>
 * <ul>
 *     <li>1 bit： 因为我们生成的 id 都是正数，所以第一个 bit 统一都是 0</li>
 *     <li>42 bit：表示时间戳，单位是毫秒。42 bit可以表示的数字多达 2^42 - 1，也就是可以标识 2 ^ 42 - 1 个毫秒值，换算成年也就是从我们指定的起始日期开始的 139 年内的时间（(2^42-1) / (3600*24*1000*366) ≈ 139）</li>
 *     <li>10 bit：表示当前服务器id，代表的是这个服务最多可以部署在 2^10 台服务器上，也就是 1024 台服务器</li>
 *     <li>10 bit：表示同一个毫秒内产生的不同序列，10 bit 可以代表的最大正整数是 2 ^ 10 = 1024，也就是可以用这个 10 bit 代表的数字来区分同一个毫秒内的 1024 个不同的 id</li>
 * </ul>
 *
 * <p>2. 将得到的 long 类型的序列号转化为62进制字符串（转换之后的字符串长度为10~11位）</p>
 *
 * <p>3. 最后将以上步骤得出的字符串补全为12位的字符串返回</p>
 *
 * @author zifangsky
 * @date 2020/11/12
 * @since 1.0.0
 */
@Slf4j
public class TidGenerator {
    /**
     * 起始的时间戳（2020-01-01 00:00:00 000）
     */
    private final static long START_STAMP = 1577808000000L;

    /* ************ 每一部分占用的位数 *************** */
    /**
     * 机器标识占用的位数
     */
    private final static long MACHINE_BIT = 10;
    /**
     * 序列号占用的位数
     */
    private final static long SEQUENCE_BIT = 10;

    /**
     * 每一部分的最大值
     */
    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long TIMESTAMP_LEFT = MACHINE_LEFT + MACHINE_BIT;

    /**
     * 机器标识
     */
    private long machineId;
    /**
     * 序列号
     */
    private long sequence = 0L;
    /**
     * 上一次生成id的时间戳
     */
    private long lastStamp = -1L;

    public TidGenerator(long machineId) {
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.machineId = machineId;
    }


    /**
     * 产生下一个TID
     */
    public String nextTid(){
        //1. 生成一个有序序列
        long sequence = nextSequenceId();
        //2. 转化为62进制
        String str = ConversionUtils.decimalToSixtyTwo(sequence);
        //3. 补全12位并返回
        return completeDigits(str);
    }

    /**
     * 产生下一个有序ID
     */
    private synchronized long nextSequenceId() {
        long nowStamp = getCurrentMillis();
        //检查是否出现了「时钟回拨」问题
        nowStamp = checkClockMovedBackwards(nowStamp);

        if (nowStamp == lastStamp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                nowStamp = getNextMillis();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }
        //记录最近一次生成id的时间戳，单位是毫秒
        lastStamp = nowStamp;

        //生成一个 64bit 的id（时间戳部分 + 机器标识部分 + 序列号部分）
        return (nowStamp - START_STAMP) << TIMESTAMP_LEFT
                | machineId << MACHINE_LEFT
                | sequence;
    }

    /**
     * 获取下一毫秒值
     */
    private long getNextMillis() {
        long millis = getCurrentMillis();
        while (millis <= lastStamp) {
            millis = getCurrentMillis();
        }
        return millis;
    }

    /**
     * 获取当前毫秒值
     */
    private long getCurrentMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 检查是否出现了「时钟回拨」问题
     */
    private long checkClockMovedBackwards(long nowStamp){
        if(nowStamp >= lastStamp){
            return nowStamp;
        }

        //如果因为某些原因出现了「时钟回拨」问题
        long diff = lastStamp - nowStamp;

        //如果时间回拨超过1秒钟，则对外抛出异常，否则暂停1秒钟后再次尝试获取
        if(diff >= TimeUnit.SECONDS.toMillis(1)){
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", diff));
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            log.error("An error occurred here", e);
            throw new RuntimeException("An error occurred here", e);
        }

        nowStamp = getCurrentMillis();
        if(nowStamp >= lastStamp){
            return nowStamp;
        }

        //如果还出现这个问题，则抛出异常
        throw new RuntimeException(String.format("Clock moved backwards again. Refusing to generate id for %d milliseconds", (lastStamp - nowStamp)));
    }

    /**
     * 补全字符串位数（最后返回12位）
     * @author zifangsky
     * @date 2020/11/13  13:41
     * @since 1.0.0
     * @param str 原始字符串
     * @return java.lang.String
     */
    private String completeDigits(String str){
        int targetDigits = 12;

        if(str.length() >= targetDigits){
            return str;
        }

        int diff = targetDigits - str.length() - 1;
        //长度标识 + 1位的随机数（可能不存在） + 原递增序列
        return ConversionUtils.decimalToSixtyTwo(str.length()) + RandomUtils.randomChars(diff, false) + str;
    }

}