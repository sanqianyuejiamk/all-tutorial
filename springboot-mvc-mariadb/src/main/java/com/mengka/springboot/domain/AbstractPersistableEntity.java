package com.mengka.springboot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author mengka
 * @version 2020/11/30
 * @since
 */
@Setter
@Getter
@MappedSuperclass
public abstract class AbstractPersistableEntity<ID> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;
}
