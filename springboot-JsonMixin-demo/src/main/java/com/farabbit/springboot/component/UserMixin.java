package com.farabbit.springboot.component;

import com.farabbit.springboot.domain.User;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.boot.jackson.JsonMixin;

/**
 * @author mengka
 * @Date 2023-01-15 22:02
 */
@JsonMixin(User.class)
public class UserMixin {

    @JsonIgnore
    private String password;
}
