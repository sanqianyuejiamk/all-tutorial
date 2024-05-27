package com.mengka.springboot.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author mengka
 * @version 1.0
 * @date 2021/5/17 20:15
 */
@Data
@Accessors(chain = true)
public class TableColumnTypeMap {

    private String columnName;
    private String columnType;
    private String javaClassName;
    private String remarks;

}