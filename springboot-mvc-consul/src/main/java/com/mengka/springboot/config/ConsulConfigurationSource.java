package com.mengka.springboot.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import com.netflix.config.PollResult;
import com.netflix.config.PolledConfigurationSource;
import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author huangyy
 * @date 2017/12/01.
 */
public class ConsulConfigurationSource implements PolledConfigurationSource {

    static final Logger logger = LogManager.getLogger(ConsulConfigurationSource.class);

    private String keyName;

    public ConsulConfigurationSource(String keyName) {
        this.keyName = keyName;
    }

    public PollResult poll(boolean initial, Object checkPoint) throws Exception {
        logger.info("--------, consul config poll keyName = {}",keyName);
        String propString = getConfigStr();
        HashMap map = Maps.newHashMap();

        if (StringUtils.isBlank(propString)) {
            return PollResult.createFull(map);
        } else {
            Properties properties = new Properties();
            properties.load(new StringReader(propString));
            properties.entrySet().forEach((e) -> {
                map.put((String) e.getKey(), e.getValue());
                if (((String) e.getKey()).startsWith("logging.level.") && LoggerFactory.getILoggerFactory() instanceof LoggerContext) {
                    LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

                    try {
                        String ex = StringUtils.substringAfter((String) e.getKey(), "logging.level.");
                        if (StringUtils.isBlank(ex)) {
                            ex = "ROOT";
                        }

                        context.getLogger(ex).setLevel(Level.valueOf((String) e.getValue()));
                    } catch (Throwable var4) {
                        logger.error("配置项{}不符合规范", e.getKey() + "=" + e.getValue());
                    }
                }

            });

            return PollResult.createFull(map);
        }
    }

    private String getConfigStr() {
        Consul consul = Consul.builder().build(); // connect to Consul on localhost
        final KeyValueClient kvClient = consul.keyValueClient();
        Optional<String> optional = kvClient.getValueAsString("foo2");
        logger.info("optional value = \n" + optional.get());
        return optional.get();
    }
}
