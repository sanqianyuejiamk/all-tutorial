package com.mkyong.repository.mapper;

import com.mkyong.base.MyBaseMapper;
import com.mkyong.base.MySelectProvider;
import com.mkyong.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface UserMapper extends MyBaseMapper<User> {

    @SelectProvider(value = MySelectProvider.class, method = "getSql")
    User select(String sql);

    /**
     * 自定义SQL：默认也会增加多租户条件
     * 参考打印的SQL
     * @return
     */
    public Integer myCount();

    public List<User> getUserAndAddr(@Param("username") String username);

    public List<User> getAddrAndUser(@Param("name") String name);
}
