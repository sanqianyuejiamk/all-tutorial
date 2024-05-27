package com.mkyong.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mkyong.model.User;
import com.mkyong.repository.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author mengka
 * @version 2021/5/5
 * @since
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
}
