package com.mengka.springboot.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author mengka
 * @Date 2022-01-14 21:36
 */
@Data
@Accessors(chain = true)
public class UserRsp implements Serializable {

    private String taskId;
}
