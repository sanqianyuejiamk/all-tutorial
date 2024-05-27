/**
 * Created on 2018/8/11.
 */
package com.mengka.springboot.service;

import com.alicp.jetcache.anno.*;
import com.mengka.springboot.domain.User;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:areyouok@gmail.com">huangli</a>
 */
public interface UserService {
    //配置一级缓存
    @Cached(name="userCache-", key="#userId", expire = 60)
    //缓存30秒钟自动刷新，从getUserById方法取一次，如果key在600秒内没有访问则不再自动刷新
    @CacheRefresh(refresh = 30, stopRefreshAfterLastAccess = 600, timeUnit = TimeUnit.SECONDS)
    //当缓存访问未命中的情况下，对并发进行的加载行为进行保护，同一个JVM中同一个key只有一个线程去加载，其它线程等待结果
    @CachePenetrationProtect
    User loadUser(long userId);

    //更新缓存
    @CacheUpdate(name="userCache-", key="#user.id", value="#user")
    User updateUser(User user);

    //删除缓存
    @CacheInvalidate(name="userCache-", key="#userId")
    void deleteUser(long userId);
}
