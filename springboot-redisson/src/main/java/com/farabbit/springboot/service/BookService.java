package com.farabbit.springboot.service;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @author mengka
 * @Date 2021-12-04 23:07
 */
@Service
public class BookService implements InitializingBean {

    private static RedissonClient client;

    private static final long port = 6379;

    public Object getMap(String name,String key){
        RMap<String, Object> map = client.getMap(name);
        return map.get(key);
    }

    public void setMap(String name,String key,String value){
        RMap<String, Object> map = client.getMap(name);
        map.put(key,value);
    }

    public void lockAcquire(String name){
        RLock lock = client.getLock(name);
        lock.lock();

        lock.unlock();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(String.format("redis://127.0.0.1:%s", port));

        client = Redisson.create(config);
    }
}
