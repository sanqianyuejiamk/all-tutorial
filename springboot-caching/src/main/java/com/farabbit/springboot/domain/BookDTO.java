package com.farabbit.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author mengka
 * @Date 2022-01-22 21:12
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private long id;

    private String name2;

    private Long price;

    private String tenantId;
}
