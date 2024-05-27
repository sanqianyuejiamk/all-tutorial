package com.mengka.springboot.zookeeper_01;

import org.apache.zookeeper.KeeperException;

import java.io.UnsupportedEncodingException;

/**
 *   https://www.baeldung.com/java-zookeeper
 *
 * @author xiafeng
 * @version farabbit2.0, 2020/11/7
 * @since farabbit2.0
 */
public interface ZKManager {

    public void create(String path, byte[] data) throws KeeperException, InterruptedException;

    public Object getZNodeData(String path, boolean watchFlag) throws KeeperException, InterruptedException, UnsupportedEncodingException;

    public void update(String path, byte[] data) throws KeeperException, InterruptedException;
}
