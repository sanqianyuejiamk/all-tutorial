package com.mkyong.base;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mengka
 * @version 2021/4/26
 * @since
 */
public class FrException extends RuntimeException{

    @Getter
    @Setter
    private String errorCode;

    public FrException(CommonCodeEnum commonCodeEnum){
        super(commonCodeEnum.getMessage());
        this.errorCode = commonCodeEnum.getCode();
    }

    public FrException(String msg){
        super(msg);
        this.errorCode = CommonCodeEnum.PARAM_VALIDATION_FAIL.getCode();
    }
}
