package com.mengka.springboot.exception;

import com.mengka.springboot.commons.enums.BaseCodeEnum;
import com.mengka.springboot.commons.enums.BusinessCodeEnum;
import lombok.Getter;
import java.text.MessageFormat;

public class OtherException extends RuntimeException {

    private static final long serialVersionUID = -8713779030609284746L;

    @Getter
    private String code;

    public OtherException(BaseCodeEnum baseCodeEnum) {
        super(baseCodeEnum.getMsg());
        this.code = baseCodeEnum.getBizCode("22001");
    }


    public OtherException(BaseCodeEnum baseCodeEnum , String message) {
        super(message);
        this.code = baseCodeEnum.getBizCode("22001");
    }

    public OtherException(String code, String message) {
        super(message);
        this.code = code;
    }

    public OtherException(BusinessCodeEnum businessCodeEnum) {
        super(businessCodeEnum.getDesc());
        this.code = businessCodeEnum.getBaseCodeEnum().getBizCode("22001");
    }

    public OtherException(BusinessCodeEnum businessCodeEnum, String extraMsg){
        super(extraMsg);
        this.code = businessCodeEnum.getBaseCodeEnum().getBizCode("22001");
    }

    public static String getMessage(BusinessCodeEnum errorCode, String extraMessage) {
        extraMessage = null != extraMessage?extraMessage:"";
        return formatMsg("错误码:{0}, 描述:{1}, 异常信息:{2}", new Object[]{errorCode.getCode(), errorCode.getDesc(), extraMessage});
    }


    public static String formatMsg(String msgTemplate, Object... positionValues) {
        try {
            return MessageFormat.format(msgTemplate, positionValues);
        } catch (Exception var5) {
            StringBuilder buf = new StringBuilder("资源信息占位符替换异常，占位符参数信息：");

            for(int i = 0; i < positionValues.length; ++i) {
                buf.append(" arg[" + i + "]=" + positionValues[i]);
            }

            return msgTemplate;
        }
    }
}