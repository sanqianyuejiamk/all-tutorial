package com.mengka.threaddemo.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author mengka
 * @Date 2024-08-07 20:35
 */
@Data
@Accessors(chain = true)
public class User {

    private String id;

    private String name;
}
