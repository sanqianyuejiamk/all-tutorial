package com.mengka.test;

import com.mkyong.model.User;
import com.mkyong.repository.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import javax.annotation.Resource;

public class TestSelectById extends BaseTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void test_deleteById(){
        userMapper.deleteById(44452070942582784L);

        User user = userMapper.selectById(44452070942582784L);
        Assert.assertNull(user);
    }
}