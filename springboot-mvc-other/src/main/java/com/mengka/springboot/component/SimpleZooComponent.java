package com.mengka.springboot.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

/**
 * @author mengka
 * @Date 2022-02-23 21:46
 */
@Slf4j
@Component
public class SimpleZooComponent {

    @Value("${lock.zookeeper.url}")
    private String zkServerUrl;

    private CuratorFramework client = null;

    @PostConstruct
    public void init() {
        try {
            connect();
        } catch (Exception e) {
            log.error("zookeeper connect  error：" , e);
        }

    }

    /**
     * 初始化连接
     */
    private void connect() {
        client = CuratorFrameworkFactory.newClient(zkServerUrl, new ExponentialBackoffRetry(100, 3));
        client.start();
    }

    public void createNode(String path)throws Exception{
        Stat stat = client.checkExists().forPath(path);
        if(stat == null){
            client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath(path, "1".getBytes());
        }
    }

    public String getNodeDate(String path){
        try {
            if(client.checkExists().forPath(path) == null){
                return null;
            }
            byte[] dataBytes = client.getData().forPath(path);
            return (dataBytes == null || dataBytes.length == 0) ? null : new String(dataBytes, "UTF-8");
        } catch (KeeperException.NoNodeException e) {
            // ignore NoNode Exception.
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        return null;
    }

    public void deleteNode(String path)throws Exception{
        client.delete()
                .guaranteed()
                .deletingChildrenIfNeeded()
                .forPath(path);
    }
}
