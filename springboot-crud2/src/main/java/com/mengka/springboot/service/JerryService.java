package com.mengka.springboot.service;

import com.mengka.springboot.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mengka
 * @Date 2022-01-28 16:22
 */
@Slf4j
@Service
public class JerryService {

    @Autowired
    private UserService userService;

    public String goHome() {
        doSomeThingA();
        doSomeThingB();

        User user = userService.findById();
        return user.getEmail();
        //return null;
    }

    // real invoke it.
    public void doSomeThingB() {
        log.info("good day");

    }

    // auto mock method by mockito
    public void doSomeThingA() {
        log.info("you should not see this message.");
    }
}
