package com.mengka.demo.enums;

import lombok.Getter;

/**
 * @author mengka
 * @Date 2022-04-03 15:05
 */
@Getter
public enum RecordStatusEnum{

    DEFAULT(0,"默认状态"),
    SIGN(1,"已签约"),
    CONFIG(2,"已配置"),
    EFFECT(3,"已生效"),
    INVALID(4,"已作废");

    private Integer value;

    private String desc;

    RecordStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static RecordStatusEnum valueOfCode(Integer value) {
        for (RecordStatusEnum codeEnum : values()) {
            if (codeEnum.getValue().equals(value)) {
                return codeEnum;
            }
        }
        return null;
    }

}
