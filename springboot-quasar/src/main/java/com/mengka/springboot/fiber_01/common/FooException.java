package com.mengka.springboot.fiber_01.common;

import lombok.Getter;
import java.text.MessageFormat;

/**
 *  转发程序异常类
 *
 * @author huangyy
 * @version cabbage-forward2.0,2017-11-1
 * @since cabbage-forward2.0
 */
public class FooException extends RuntimeException {

    private static final long serialVersionUID = -8713779030609284746L;

    @Getter
    private String code;

    public FooException(String code, String message) {
        super(message);
        this.code = code;
    }

    public FooException(String message){
        super(message);
    }

    public FooException(BusinessCodeEnum businessCodeEnum) {
        super(businessCodeEnum.getDesc());
    }

    public FooException(BusinessCodeEnum businessCodeEnum, String extraMsg) {
        super(extraMsg);
        this.code = businessCodeEnum.getCode();
    }

    public static String getMessage(BusinessCodeEnum errorCode, String extraMessage) {
        extraMessage = null != extraMessage ? extraMessage : "";
        return formatMsg("错误码:{0}, 描述:{1}, 异常信息:{2}", new Object[]{errorCode.getCode(), errorCode.getDesc(), extraMessage});
    }


    public static String formatMsg(String msgTemplate, Object... positionValues) {
        try {
            return MessageFormat.format(msgTemplate, positionValues);
        } catch (Exception var5) {
            StringBuilder buf = new StringBuilder("资源信息占位符替换异常，占位符参数信息：");

            for (int i = 0; i < positionValues.length; ++i) {
                buf.append(" arg[" + i + "]=" + positionValues[i]);
            }

            return msgTemplate;
        }
    }
}
