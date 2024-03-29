package com.mengka.springboot.model;

import lombok.Data;
import java.io.Serializable;

/**
 * @author mengka
 * @Date 2022-01-14 21:32
 */
@Data
public class UserReq implements Serializable {

    private Integer userId;

    private String title;

    private String body;
}
