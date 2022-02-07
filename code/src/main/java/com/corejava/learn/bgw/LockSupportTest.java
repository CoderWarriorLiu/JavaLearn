package com.corejava.learn.bgw;


import java.util.concurrent.locks.LockSupport;


public class LockSupportTest {

    static Thread t1 = null;
    static Thread t2 = null;

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] cI = "ABCDEFG".toCharArray();

        t1 = new Thread(() -> {
            for (char c : aI) {

                System.out.println(c);
                LockSupport.unpark(t2);
                LockSupport.park();
                System.out.println("t1 park");
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (char c : cI) {
                LockSupport.park();
                System.out.println("t2 park");
                System.out.println(c);
                LockSupport.unpark(t1);

            }
        }, "t2");

        t2.start();
        t1.start();


    }
}
