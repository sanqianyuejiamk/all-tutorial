package com.mengka.springboot.vo;

/**
 * @author mengka
 * @version 2021/4/18
 * @since
 */
public class DebugMode {
    public final static String MODE_ON = "ON";
    public final static String MODE_OFF = "OFF";

    private String mode;
    private String sdkId;
    private String env;


    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getSdkId() {
        return sdkId;
    }

    public void setSdkId(String sdkId) {
        this.sdkId = sdkId;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }
}