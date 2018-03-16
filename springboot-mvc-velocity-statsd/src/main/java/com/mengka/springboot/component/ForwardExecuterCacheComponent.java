package com.mengka.springboot.component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.log4j.Log4j2;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.concurrent.TimeUnit;

/**
 *  转发插件执行器缓存工具类
 *
 * @author huangyy
 * @version cabbage-forward2.0,2018-2-27
 * @since cabbage-forward2.0
 */
@Log4j2
public class ForwardExecuterCacheComponent {

    private final static Cache<String, Class> cache = CacheBuilder.newBuilder()
            //设置cache的初始大小为10，要合理设置该值
            .initialCapacity(300)
            //设置并发数为5，即同一时间最多只能有20个线程往cache执行写入操作
            .concurrencyLevel(20)
            //设置cache中的数据在写入之后的存活时间为10mins
            .expireAfterWrite(10, TimeUnit.MINUTES)
            //构建cache实例
            .build();

    public Class getClasscfg(String packagePath, String version, String className) {
        String key = packagePath + ":" + version;
        Class clazz = cache.getIfPresent(key);
        if (clazz != null) {
            return clazz;
        }
        try {
            URL url = new URL("file:" + packagePath);
            URLClassLoader myClassLoader = new URLClassLoader(new URL[]{url}, Thread.currentThread()
                    .getContextClassLoader());
            Class myClass = myClassLoader.loadClass(className);
            cache.put(key,myClass);
            return myClass;
        }catch (Exception e){
            log.error("getMethodcfg erorr!",e);
        }
        return null;
    }

    public static ForwardExecuterCacheComponent getInstance() {
        return ForwardExecuterCacheComponentHolder.forwardExecuter_holder;
    }

    private static class ForwardExecuterCacheComponentHolder {
        private static ForwardExecuterCacheComponent forwardExecuter_holder = new ForwardExecuterCacheComponent();
    }
}
