package com.farabbit.springboot.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author mengka
 * @Date 2022-03-25 21:24
 */
@Data
@Accessors(chain = true)
public class RetryRspDto implements Serializable {

    private String retCode;

    private String message;
}
