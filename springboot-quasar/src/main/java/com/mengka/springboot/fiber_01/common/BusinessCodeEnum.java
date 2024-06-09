package com.mengka.springboot.fiber_01.common;

import lombok.Getter;

/**
 *  转发程序业务编码
 *
 * @author huangyy
 * @version cabbage-forward2.0,2017-11-1
 * @since cabbage-forward2.0
 */
public enum BusinessCodeEnum {

    /************002成功********************/
    REQUEST_SUCCESS("200", "请求成功"),

    SYSTEM_ERROR("1002", "系统错误");

    @Getter
    private String code;

    @Getter
    private String desc;

    BusinessCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static BusinessCodeEnum valueOfCode(String code) {
        for (BusinessCodeEnum codeEnum : values()) {
            if (codeEnum.getCode().equals(code)) {
                return codeEnum;
            }
        }
        return null;
    }

    public Integer getCodeValue(){
        return Integer.valueOf(this.code);
    }
}
