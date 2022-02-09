package com.corejava.learn.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class ZKCuratorTest {

    private static final String CLUSTER = "127.0.0.1:2181";

    private static final String PATH = "/curcity";

    private static final String CUR_SPACE = "curspace";

    public static void main(String[] args) throws Exception {

        CuratorFramework client = CuratorFrameworkFactory
                .builder()
                .connectString(CLUSTER)
                .sessionTimeoutMs(15000)
                .connectionTimeoutMs(13000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 1))
                .namespace(CUR_SPACE)
                .build();

        client.start();

        client.create().forPath(PATH, "curcity".getBytes());

        //get data
        byte[] bt = client.getData().forPath(PATH);

        System.out.println("getData:" + new String(bt));

        client.delete().forPath(PATH);

    }
}
