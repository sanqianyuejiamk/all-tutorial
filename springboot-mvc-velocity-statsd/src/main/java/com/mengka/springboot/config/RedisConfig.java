package com.mengka.springboot.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.JedisPublicMetrics;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import java.util.Map;

/**
 * @author huangyy
 * @version cabbage-forward2.0,2018-05-27
 * @since cabbage-forward2.0
 */
@Log4j2
@Configuration
public class RedisConfig {

    @Bean
    @Autowired
    public JedisPublicMetrics jedisPublicMetrics(Map<String,JedisConnectionFactory> factories) {
        return new JedisPublicMetrics(factories);
    }

}
