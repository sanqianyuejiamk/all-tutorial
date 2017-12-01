package com.mengka.springboot.config;

import com.google.common.collect.Maps;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

/**
 * @author huangyy
 * @date 2017/12/01.
 */
public class MyPropertySourceLocator implements PropertySourceLocator {

    @Override
    public PropertySource<?> locate(Environment environment) {
        if (environment instanceof ConfigurableEnvironment) {
            ConfigurableEnvironment env = (ConfigurableEnvironment) environment;
            String serviceName = env.getProperty("mengka.cloud.service.name");
            String serviceTag = env.getProperty("service.tag");
            if ("local".equalsIgnoreCase(serviceTag)) {
                return null;
            } else {
                String consulKey = String.format("service/%s/%s/config", new Object[]{serviceName, serviceTag});

                ConsulPropertySource myPropertySource = new ConsulPropertySource("myPropertySource", Maps.newHashMap());
                return myPropertySource;
            }
        } else {
            return null;
        }
    }
}