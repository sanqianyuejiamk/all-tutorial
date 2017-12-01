package com.mengka.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  这个是一个启动配置类（一种优先级很高的配置类）
 *
 * @author huangyy
 * @date 2017/12/01.
 */
@Configuration
public class ConsulConfig {

    @Bean
    public MyPropertySourceLocator myPropertySourceLocator() {
        return new MyPropertySourceLocator();
    }
}
