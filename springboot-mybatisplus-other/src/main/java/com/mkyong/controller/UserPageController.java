package com.mkyong.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mkyong.model.User;
import com.mkyong.model.UserDTO;
import com.mkyong.repository.mapper.UserMapper;
import com.mkyong.transfor.UserTransf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping(path = "/user-page")
public class UserPageController {

    private final UserTransf userTransf = UserTransf.INSTANCE;
    @Resource
    private UserMapper userMapper;

    /**
     *  mybatis-plus分页模型
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/selectPage", method = RequestMethod.POST)
    public UserDTO selectPage(@RequestParam(value = "id")String id) {

        Page<User> mpPage = userMapper.selectPage(new Page<>(1, 10), Wrappers.<User>query().eq("gmt_create", "2021-05-05 16:07:50"));
        long total = mpPage.getTotal();

        List<User> userList = mpPage.getRecords();

        log.info("---userList--- {}",userList.size());
        return userTransf.toDTO(userList.get(0));
    }

    /**
     *  pagehelper查询
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/selectPage2", method = RequestMethod.POST)
    public UserDTO selectPage2(@RequestParam(value = "id")String id) {

        // pagehelper
        PageInfo<User> info = PageHelper.startPage(1, 10).doSelectPageInfo(() -> userMapper.selectList(Wrappers.<User>lambdaQuery().eq(User::getGmtCreate, "2021-05-05 16:07:50").orderByAsc(User::getAge)));
        long total = info.getTotal();

        List<User> userList = info.getList();

        log.info("---userList--- {}",userList.size());
        return userTransf.toDTO(userList.get(0));
    }
}
