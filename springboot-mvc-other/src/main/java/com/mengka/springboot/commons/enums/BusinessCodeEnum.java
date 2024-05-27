package com.mengka.springboot.commons.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * 业务码均为6位:
 * <br/>
 * 1、错误码规范TSSXXX:T代表成功与否,SS代表系统位XXX代表编码
 * T: 0 成功 1 请求端错误 2 后端系统错误 9系统内部错误
 * 业务核心系统编号02
 * XXX代表编码
 * see http://192.168.1.82/Inner-Document/dev-rule/wikis/inner-service-rescode
 * <p>
 * <br/>
 * Created on 2016/7/29 14:07.
 */
public enum BusinessCodeEnum {

    /************002成功********************/
    ID_CARD_AUTH_SUCCESS("002003", "身份证实名认证成功", null),
    ID_CARD_AUTH_FALSE("002004", "身份证实名认证不一致", null),

    /************102请求端参数错误********************/

    SYSTEM_ERROR("102000", "请求参数错误:" , BaseCodeEnum.REQUEST_PRECONDITION_FAILED),
    REQUEST_PARAM_ERROR("102000", "请求参数错误:" , BaseCodeEnum.REQUEST_PRECONDITION_FAILED),
    NONE_EXIST_REFERENCES("102030", "推荐人不存在", BaseCodeEnum.REQUEST_PRECONDITION_FAILED),
    REFERENCE_INFO_LACK("102031", "推荐人未完善身份信息", BaseCodeEnum.REQUEST_PRECONDITION_FAILED),
    NOT_NEW_USER("102032", "登录用户不是新用户,无法使用推荐人", BaseCodeEnum.REQUEST_PRECONDITION_FAILED),
    RESOURCE_EXISTED("102002","资源已存在", BaseCodeEnum.REQUEST_PRECONDITION_FAILED),

    ID_CARD_AGE_NOT_IN_RANGE("102003", "身份证年龄不符合要求", BaseCodeEnum.REQUEST_PRECONDITION_FAILED),
    ID_CARD_ERROR("102004", "身份证号码错误", BaseCodeEnum.REQUEST_PRECONDITION_FAILED),
    ID_CARD_ALREADY_REGISTER("102005", "您的身份信息已被注册", BaseCodeEnum.REQUEST_PRECONDITION_FAILED),
    ID_CARD_VALIDATE_ERROR("102006", "您的身份信息实名校验不通过", BaseCodeEnum.REQUEST_PRECONDITION_FAILED);

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String desc;

    @Getter
    @Setter
    private BaseCodeEnum baseCodeEnum;

    BusinessCodeEnum(String code, String desc, BaseCodeEnum baseCodeEnum) {
        this.code = code;
        this.desc = desc;
        this.baseCodeEnum = baseCodeEnum;
    }

    /**
     * 根据key获取value
     *
     * @param key
     * @return
     */
    public static String getValue(String key) {
        for (BusinessCodeEnum bc : BusinessCodeEnum.values()) {
            if (bc.getCode().equals(key)) {
                return bc.getDesc();
            }
        }
        return null;
    }
}
