package com.mengka.springboot.controller;

import com.mengka.springboot.zookeeper_01.ZKManagerImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;

/**
 * @author xiafeng
 * @version farabbit2.0, 2020/11/17
 * @since farabbit2.0
 */
@Slf4j
@RestController
public class HelloController {

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private ZKManagerImpl zkManager;

    /**
     *   http://127.0.0.1:8076/hello
     *
     * @return
     */
    @GetMapping(value="/hello")
    public String zipkinService1() throws InterruptedException, UnsupportedEncodingException, KeeperException {
        log.info("HelloController 1..");

        final String zkPath = "/MyFirstZnode";
        Object obj = zkManager.getZNodeData(zkPath,false);

        return "Node[/MyFirstZNode] = "+obj;
    }

    /**
     *  http://127.0.0.1:8076/create_node
     *
     */
    @GetMapping(value="/create_node")
    public String create1() throws InterruptedException, UnsupportedEncodingException, KeeperException {
        log.info("HelloController create zk node..");

        final String zkPath = "/MyFirstZnode";

        // data in byte array
        byte[] data = "My first zookeeper app".getBytes(); // Declare data


        zkManager.create(zkPath,data);

        return "Node[/MyFirstZNode] = "+new String(data);
    }

    @GetMapping(value="/exist_node")
    public String exist1() throws InterruptedException, UnsupportedEncodingException, KeeperException {
        log.info("HelloController exist zk node..");

        final String zkPath = "/MyFirstZnode";

        // Stat checks the path of the znode
        Stat stat = zkManager.znode_exists(zkPath);

        if(stat != null) {
            log.info("-------, Node exists and the node version is " + stat.getVersion());
        } else {
            log.info("-------, Node does not exists");
        }

        return "Node[/MyFirstZNode]";
    }
}
