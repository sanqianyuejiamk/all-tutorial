package com.mkyong.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.gson.Gson;
import com.mkyong.model.User;
import com.mkyong.model.UserDTO;
import com.mkyong.model.UserReq;
import com.mkyong.repository.mapper.UserMapper;
import com.mkyong.transfor.UserTransf;
import com.mkyong.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 *   /Users/hyy044101331/work_github/work_mybatisplus/mybatis-plus-samples-master
 *
 *
 * @author mengka
 * @version 2021/4/25
 * @since
 */
@Slf4j
@RestController
@Validated
@RequestMapping(path = "/user")
public class UserController {

    private final UserTransf userTransf = UserTransf.INSTANCE;

    @Resource
    private UserMapper userMapper;



    @RequestMapping(path = "/t1", method = RequestMethod.POST)
    public UserDTO t1(@Valid @RequestBody UserReq userReq) {
        User user = userMapper.select("select * from user where reqst_no='e7c51316fb284c5e92d2266b44746f17'");



        log.info("---user--- {}",new Gson().toJson(user));
        return userTransf.toDTO(user);
    }

    @RequestMapping(path = "/del", method = RequestMethod.POST)
    public int delete(@RequestParam(value = "id")String id) {

        int value = userMapper.delete(new QueryWrapper<User>().lambda().eq(User::getName,"x31"));

        log.info("---user--- {}",value);
        return value;
    }

    @RequestMapping(path = "/t2", method = RequestMethod.POST)
    public UserDTO t2() {

        Date date = TimeUtil.toDate("2021-05-01 00:00:00",TimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
        Date date2 = TimeUtil.toDate("2021-06-01 00:00:00",TimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);

        /**
         *  SELECT id,name,age,email,gmt_create,gmt_modified,reqst_no FROM user WHERE (gmt_create >= '2021-05-01 00:00:00');
         *
         *  SELECT id,name,age,email,gmt_create,gmt_modified,reqst_no FROM user WHERE (gmt_create >= '2021-05-01 00:00:00' AND gmt_create < '2021-06-01 00:00:00');
         */
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().ge(User::getGmtCreate,date);
        wrapper.lambda().lt(User::getGmtCreate,date2);
        List<User> list = userMapper.selectList(wrapper);

        User user = list.get(0);
        log.info("---user--- {}",new Gson().toJson(user));
        return userTransf.toDTO(user);
    }


    /**
     *   ==>  Preparing: UPDATE user SET name=?, age=? WHERE (reqst_no = ?)
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public int update(@RequestParam(value = "id")String id) {

        int value = userMapper.update(
                new User().setName("mp"),
                Wrappers.<User>lambdaUpdate()
                        .set(User::getAge, 10)
                        .eq(User::getReqstNo, "43604b4ab33a4040960effc70939b25c")
        );


        log.info("---user--- {}",value);
        return value;
    }
}
