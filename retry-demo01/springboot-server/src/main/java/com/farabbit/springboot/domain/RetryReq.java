package com.farabbit.springboot.domain;

import lombok.Data;
import java.io.Serializable;

/**
 * @author mengka
 * @Date 2022-03-18 21:12
 */
@Data
public class RetryReq implements Serializable {

    private String id;

    private String name;

    private String requestNo;
}
