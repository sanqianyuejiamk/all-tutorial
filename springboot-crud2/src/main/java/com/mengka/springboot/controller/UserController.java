package com.mengka.springboot.controller;

import com.mengka.springboot.domain.UserLocationDTO;
import com.mengka.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author mengka
 * @Date 2022-01-27 14:59
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *   http://127.0.0.1:8071/users-location
     *
     * @return
     */
    @GetMapping("users-location")
    public List<UserLocationDTO> getAllUserLocation(){
        return userService.getAllUsersLocation();
    }

    /**
     *  OneToMany测试
     *  http://127.0.0.1:8071/users-book
     *
     * @return
     */
    @GetMapping("users-book")
    public List<UserLocationDTO> getAllUserBook(){
        return userService.getAllUsersBook();
    }
}
