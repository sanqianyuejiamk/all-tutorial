package com.mkyong.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author mengka
 * @version 2021/4/25
 * @since
 */
@Data
@AllArgsConstructor
public class UserDTO implements Serializable {

    private Long id;

    private String name;

    private Integer age;

    private String email2;

    private String reqstNo;

    public UserDTO(){}
}
