package com.mengka.springboot.commons.enums;

import lombok.Getter;
import lombok.Setter;

public enum BaseCodeEnum {

    REQUEST_PRECONDITION_FAILED("500","系统哦你错误","22001");

    @Getter
    @Setter
    private String msg;

    @Getter
    @Setter
    private String desc;

    @Getter
    @Setter
    private String bizCode;

    BaseCodeEnum(String msg,String desc,String bizCode){
        this.msg = msg;
        this.desc = desc;
        this.bizCode = bizCode;
    }

    public String getBizCode(String s){
        for (BaseCodeEnum tmp : values()) {
            if (tmp.getBizCode().equals(s)) {
                return tmp.getBizCode();
            }
        }
        return null;
    }
}
