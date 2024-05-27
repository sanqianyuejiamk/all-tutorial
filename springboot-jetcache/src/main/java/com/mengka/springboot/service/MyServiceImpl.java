/**
 * Created on 2018/8/11.
 */
package com.mengka.springboot.service;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import com.mengka.springboot.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:areyouok@gmail.com">huangli</a>
 */
@Slf4j
@Component
public class MyServiceImpl implements MyService {
    @CreateCache(name = "myServiceCache", expire = 60)
    private Cache<String, String> cache;

    @Autowired
    private UserService userService;

    @Override
    public void createCacheDemo() {
        String myValue1 = cache.get("myKey");
        cache.put("myKey", "myValue");
        String myValue2 = cache.get("myKey");
        log.info("get 'myKey' from cache:" + myValue2);
    }

    @Override
    public void cachedDemo() {
        userService.loadUser(1);
        User user = userService.loadUser(1);
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
