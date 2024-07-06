package com.mengka.threaddemo.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by jjmendoza on 14/7/2017.
 */
@Entity
@Table(name = "note")
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Note extends AbstractAuditableEntity<Long> implements Serializable {

    @Column
    private String title;

    @Column
    private String content;
}
