package com.mkyong.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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

    private Long tenantId;

    /**
     *  关联表user_addr
     */
    private String addrName;

    /**
     *  String ==>> Date日期格式
     */
    private Date registerDate;

    public UserDTO(){}
}
