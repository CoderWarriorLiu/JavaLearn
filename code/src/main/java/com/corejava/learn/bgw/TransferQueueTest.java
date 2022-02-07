package com.corejava.learn.bgw;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class TransferQueueTest {

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] cI = "ABCDEFG".toCharArray();

        TransferQueue transferQueue = new LinkedTransferQueue();

        new Thread(() -> {
            for(char c: aI) {
                try {
                    System.out.println(transferQueue.take());
                    transferQueue.transfer(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "t1").start();

        new Thread(() -> {
            for(char c: cI) {
                try {
                    transferQueue.transfer(c);
                    System.out.println(transferQueue.take());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "t2").start();
    }
}
