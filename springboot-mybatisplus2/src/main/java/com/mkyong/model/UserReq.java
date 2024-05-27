package com.mkyong.model;

import lombok.Data;
import javax.validation.constraints.*;
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
public class UserReq implements Serializable {

    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 10)
    private String name;

    @Min(3)
    @Max(30)
    private Integer age;

    @Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
    private String email;

    @NotNull
    @NotBlank
    private String reqstNo;

    /**
     *  String ==>> Date日期格式
     */
    private Date registerDate;
}
