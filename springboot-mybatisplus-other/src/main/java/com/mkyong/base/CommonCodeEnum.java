package com.mkyong.base;

import lombok.Getter;

/**
 * @author mengka
 * @version 2021/4/26
 * @since
 */
public enum CommonCodeEnum implements CommonResultCode {

    PARAM_VALIDATION_FAIL("999500","参数校验失败",0);

    @Getter
    private String code;

    @Getter
    private String message;

    @Getter
    private Integer status;

    CommonCodeEnum(String code, String message, Integer status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public static CommonCodeEnum valueOfCode(String code) {
        for (CommonCodeEnum codeEnum : values()) {
            if (codeEnum.getCode().equals(code)) {
                return codeEnum;
            }
        }
        return null;
    }

    @Override
    public String getResultCode() {
        return null;
    }

    @Override
    public String getResultMsg() {
        return null;
    }
}
