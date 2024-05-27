package com.mkyong.controller;

import com.alibaba.fastjson.JSON;
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
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
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

        User user2 = userMapper.selectById(user.getId());

        log.info("---user--- {}",new Gson().toJson(user));
        return userTransf.toDTO(user);
    }

    /**
     * 【user保存接口】
     * 1）实体对象中，有个字段的类型是Date，但是实际传参的是字符串类型：2021-01-03 10:20:15
     *
     *  http://127.0.0.1:8083/user/save
     *
     *  curl -v -X POST http://127.0.0.1:8083/user/save \
     *     -H "Content-Type: application/json" \
     *     -d '{ "id": 10, "name": "shuxue", "age":20, "email":"xiafeng@qq.com", "reqstNo":"A23ISqq77IX2","registerDate":"2021-12-09 12:00:00" }'
     *
     * @param userReq
     * @return
     */
    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public UserDTO save(@Valid @RequestBody UserReq userReq) {
        log.info("调用 String ==>> Date日期格式 接口 {}",JSON.toJSONString(userReq));

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userReq, userDTO);

        log.info("userDTO time = {}",TimeUtil.toDate(userDTO.getRegisterDate(),TimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
        return userDTO;
    }

    @RequestMapping(path = "/del", method = RequestMethod.POST)
    public int delete(@RequestParam(value = "id")String id) {

        int value = userMapper.delete(new QueryWrapper<User>().lambda().eq(User::getName,"x31"));

        boolean aa = userMapper.deleteById(3L) > 0;

        log.info("---user--- {}",value);
        return value;
    }

    /**
     *   ==>  Preparing: UPDATE user SET name=?, age=? WHERE (reqst_no = ?)
     *
     *   update(et,ew) et:必须带上version的值才会触发乐观锁
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public int update(@RequestParam(value = "id")String id) {

        int value = userMapper.update(
                new User().setName("mp").setVersion(1),
                Wrappers.<User>lambdaUpdate()
                        .set(User::getAge, 10)
                        .eq(User::getReqstNo, "43604b4ab33a4040960effc70939b25c")
        );


        boolean aa = userMapper.updateById(new User().setId(1L).setVersion(2).setName("mp")) > 0;

        log.info("---user--- {}",value);
        return value;
    }

    /**
     *  自定义SQL：默认也会增加多租户条件
     *  参考打印的SQL
     *
     *   ==>  Preparing: SELECT count(1) FROM user WHERE tenant_id = 1
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/tenant1", method = RequestMethod.POST)
    public int tenant1(@RequestParam(value = "id")String id) {

        int value = userMapper.myCount();

        log.info("---user--- {}",value);
        return value;
    }

    /**
     *
     *  ==>  Preparing: SELECT a.name AS addr_name, u.id, u.name
     *  FROM user_addr a LEFT JOIN user u ON u.id = a.user_id AND u.tenant_id = 1
     *
     *
     *  ==>  Preparing: SELECT u.id, u.name, a.name AS addr_name
     *  FROM user u LEFT JOIN user_addr a ON a.user_id = u.id WHERE u.name LIKE concat(concat('%', ?), '%') AND u.tenant_id = 1
     *
     *  ==> Parameters: x11(String)
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/tenant2", method = RequestMethod.POST)
    public UserDTO tenant2(@RequestParam(value = "id")String id) {

        List<User> users = userMapper.getAddrAndUser(null);
        users.stream().forEach(System.out::println);

        List<User> users2 = userMapper.getUserAndAddr("x11");
        users2.stream().forEach(System.out::println);

        log.info("---user--- {}",users.size());
        return userTransf.toDTO(users2.get(0));
    }
}
