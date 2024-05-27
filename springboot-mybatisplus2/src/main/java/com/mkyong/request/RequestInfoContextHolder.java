package com.mkyong.request;

/**
 * @author mengka
 * @version 2021/4/26
 * @since
 */
public class RequestInfoContextHolder {

    private static ThreadLocal<String> applyHolder = new ThreadLocal<>();

    public static String getApplyHolder(){
        return applyHolder.get();
    }

    public static void setApplyHolder(String status){
        applyHolder.set(status);
    }
}
