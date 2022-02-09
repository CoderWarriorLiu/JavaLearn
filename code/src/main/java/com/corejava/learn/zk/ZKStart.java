package com.corejava.learn.zk;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;


/**
 * test java op zk
 *
 * @date 2022-02-08
 */
public class ZKStart {

    private static final String CLUSTER = "127.0.0.1:2181";

    private static final String PATH = "/city";

    public static void main(String[] args) {

        ZkClient zkClient = new ZkClient(CLUSTER);
        if (!zkClient.exists(PATH)) {
            String nodeName = zkClient.create(PATH, "allcity", CreateMode.PERSISTENT);

            System.out.println("nodeName = " + nodeName);

        }
        Object ob = zkClient.readData(PATH);

        System.out.println(ob);

        zkClient.subscribeDataChanges(PATH, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("datepath：" + s + "change");
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("datepath：" + s + "delete");
            }
        });


        if (zkClient.exists(PATH)) {

            zkClient.writeData(PATH, "city");
            ob = zkClient.readData(PATH);
            System.out.println(ob);
        }


    }
}
