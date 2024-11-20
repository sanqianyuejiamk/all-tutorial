package com.farabbit.springboot.controller;

import com.farabbit.springboot.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author mengka
 * @Date 2023-01-15 21:57
 */
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @GetMapping("/user")
    public User getInstructors() {
        return new User(1L,"mengka","my-secret-password");
    }

}
