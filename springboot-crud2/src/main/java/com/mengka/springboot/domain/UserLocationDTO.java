package com.mengka.springboot.domain;

import lombok.Data;

/**
 * @author mengka
 * @Date 2022-01-27 14:53
 */
@Data
public class UserLocationDTO {

    private long userId;

    private String email;

    private String place;

    private double longitude;

    private double latitude;
}
