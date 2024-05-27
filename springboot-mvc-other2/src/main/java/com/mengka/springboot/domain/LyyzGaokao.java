package com.mengka.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author mengka
 * @version 2020/12/11
 * @since
 */
@Entity
@Table(name = "lyyz_gaokao")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LyyzGaokao implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer yearOfGraduation;

    @Column
    private String name;

    @Column
    private Integer sex;

    @Column
    private String wl;

    @Column
    private String university;

    @Column
    private String middleSchool;

    @Column
    private String primarySchool;


}
