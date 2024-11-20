package com.farabbit.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author mengka
 * @Date 2023-01-15 21:58
 */
@Data
@AllArgsConstructor
public class User {

    private Long id;

    private String username;

    private String password;
}
