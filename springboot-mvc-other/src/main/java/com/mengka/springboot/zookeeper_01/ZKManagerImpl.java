package com.mengka.springboot.zookeeper_01;

import lombok.extern.log4j.Log4j2;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author xiafeng
 * @version farabbit2.0, 2020/11/7
 * @since farabbit2.0
 */
@Log4j2
@Service
public class ZKManagerImpl implements ZKManager {

    private static ZooKeeper zkeeper;
    private static ZKConnection zkConnection;

    public ZKManagerImpl() {
        initialize();
    }

    private void initialize() {
        try {
            zkConnection = new ZKConnection();
            zkeeper = zkConnection.connect("localhost");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() throws InterruptedException {
        zkConnection.close();
    }

    /**
     *  create a znode
     *
     * @param path
     * @param data
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Override
    public void create(String path, byte[] data) throws KeeperException, InterruptedException {
        zkeeper.create(
                path,
                data,
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
    }

    /**
     *  Check the Existence of a Znode
     *
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public Stat znode_exists(String path)throws KeeperException,InterruptedException{
        return zkeeper.exists(path, true);
    }

    @Override
    public Object getZNodeData(String path, boolean watchFlag) throws KeeperException, InterruptedException, UnsupportedEncodingException {
        byte[] b = null;
        b = zkeeper.getData(path, null, null);
        return new String(b, "UTF-8");
    }

    @Override
    public void update(String path, byte[] data) throws KeeperException, InterruptedException {
        int version = zkeeper.exists(path, true).getVersion();
        zkeeper.setData(path, data, version);
    }
}
