package com.mkyong.controller;

import com.mkyong.model.User;
import com.mkyong.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author mengka
 * @version 2021/5/5
 * @since
 */
@Slf4j
@RestController
@Validated
@RequestMapping(path = "/user-serv")
public class UserServController {

    @Resource
    private UserService userService;

    @RequestMapping(path = "/testBatch", method = RequestMethod.POST)
    public Boolean testBatch(@RequestParam(value = "id")String id) {

        List<User> batchUsers = Lists.newArrayList();
        batchUsers.add(User.builder().name("x31").age(10).email("x31@farabbit.com").build());
        batchUsers.add(User.builder().name("x32").age(12).email("x34@farabbit.com").build());
        batchUsers.add(User.builder().name("x33").age(13).email("x33@farabbit.com").build());
        boolean falg = userService.saveBatch(batchUsers);

        log.info("---user--- {}",falg);
        return falg;
    }
}
