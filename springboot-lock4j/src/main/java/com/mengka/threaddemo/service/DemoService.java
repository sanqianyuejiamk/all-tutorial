package com.mengka.threaddemo.service;

import com.baomidou.lock.annotation.Lock4j;
import com.mengka.threaddemo.model.User;
import org.springframework.stereotype.Service;

/**
 * @author mengka
 * @Date 2024-08-07 20:34
 */
@Service
public class DemoService {

    //完全配置，支持spel
    @Lock4j(keys = {"#user.id"}, expire = 60000, acquireTimeout = 1000)
    public User customMethod(User user) throws InterruptedException {
        System.out.println("进入锁方法内部，用户信息为："+user);
        Thread.sleep(5000);
        System.out.println("锁方法执行结束");
        return user;
    }
}
