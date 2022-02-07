package com.corejava.learn.bgw;

public class WaitNotifyTest {

    public static void main(String[] args) {

        final Object o = new Object();
        char[] aI = "1234567".toCharArray();
        char[] cI = "ABCDEFG".toCharArray();


        new Thread(() -> {
            synchronized (o) {
                for (char c : aI) {

                    System.out.println(c);

                    try {

                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }

        }, "t1").start();

        new Thread(() -> {
            synchronized (o) {
                for (char c : cI) {

                    System.out.println(c);

                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();

            }


        }, "t2").start();

    }


}
