package com.mkyong.repository.mapper;

import com.mkyong.base.MyBaseMapper;
import com.mkyong.base.MySelectProvider;
import com.mkyong.model.User;
import org.apache.ibatis.annotations.SelectProvider;

public interface UserMapper extends MyBaseMapper<User> {

    @SelectProvider(value = MySelectProvider.class, method = "getSql")
    User select(String sql);
}
