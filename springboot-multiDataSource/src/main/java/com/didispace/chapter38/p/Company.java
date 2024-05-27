package com.didispace.chapter38.p;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author mengka
 * @Date 2022-01-22 15:38
 */
@Entity
@Data
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue
    private String id;

    private String name;

    private Date createTime;

    private Date updateTime;
}
