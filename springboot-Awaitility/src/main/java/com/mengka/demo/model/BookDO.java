package com.mengka.demo.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author mengka
 * @Date 2024-05-27 19:55
 */
@Data
@Accessors(chain = true)
public class BookDO {

    private String name;

    private String groupId;
}
