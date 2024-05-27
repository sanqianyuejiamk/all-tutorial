package com.mengka.springboot.domain;

import lombok.*;
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

    private String title;
    private String content;

}
