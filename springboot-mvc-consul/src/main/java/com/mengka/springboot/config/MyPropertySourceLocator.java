package com.mengka.springboot.config;

import com.google.common.collect.Maps;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

/**
 * @author huangyy
 * @date 2017/12/01.
 */
public class MyPropertySourceLocator implements PropertySourceLocator {

    @Override
    public PropertySource<?> locate(Environment environment) {
        ConsulPropertySource myPropertySource = new ConsulPropertySource("myPropertySource", Maps.newHashMap());
        return myPropertySource;
    }
}
