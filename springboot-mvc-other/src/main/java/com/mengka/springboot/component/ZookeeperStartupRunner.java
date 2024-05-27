package com.mengka.springboot.component;

import com.mengka.springboot.zookeeper_01.ZKManagerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author xiafeng
 * @version farabbit2.0, 2020/11/7
 * @since farabbit2.0
 */
@Order(value = 13)
@Component
@Slf4j
public class ZookeeperStartupRunner implements CommandLineRunner {

    @Autowired
    private ZKManagerImpl zkManager;

    @Override
    public void run(String... args) throws Exception {
        log.info("----------, ZookeeperStartupRunner run... ");

//        final String zkPath = "/MyFirstZNode";
//        Object obj = zkManager.getZNodeData(zkPath,false);
//        log.info("------------, obj = {}",obj);
//
    }
}
