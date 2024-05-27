package com.mengka.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jjmendoza on 14/7/2017.
 */
@Entity
@Table(name = "note")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Note extends AbstractAuditableEntity<Long> implements Serializable {

    @Column
    private String title;

    @Column
    private String content;
}
