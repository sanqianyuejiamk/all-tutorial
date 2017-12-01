package com.mengka.springboot.config;

import java.util.Iterator;
import java.util.Map;
import org.springframework.core.env.MapPropertySource;
import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicConfiguration;
import com.netflix.config.FixedDelayPollingScheduler;
import com.netflix.config.PolledConfigurationSource;

/**
 * @author huangyy
 * @date 2017/12/01.
 */
public class ConsulPropertySource extends MapPropertySource {

    private DynamicConfiguration props;

    public ConsulPropertySource(String name, Map<String, Object> source) {
        super(name, source);// 初始化
        /**
         * 从consul上读取属性并存入netflix config
         */
        PolledConfigurationSource configSource = new ConsulConfigurationSource(name);// 定义读取配置的源头
        DynamicConfiguration configuration = new DynamicConfiguration(configSource, new FixedDelayPollingScheduler());
        ConfigurationManager.install(configuration);
        /**
         * 将属性存入PropertySource
         */
        Iterator it = configuration.getKeys();
        while (it.hasNext()) {
            String key = (String) it.next();
            this.source.put(key, configuration.getProperty(key));
        }
    }
}
