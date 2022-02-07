package com.corejava.learn.bgw;

import com.sun.org.apache.xml.internal.security.keys.storage.implementations.CertsInFilesystemDirectoryResolver;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionTest {

    public static void main(String[] args) {

        char[] aI = "1234567".toCharArray();
        char[] cI = "ABCDEFG".toCharArray();
        char[] dI = "abcdefg".toCharArray();

        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();

        new Thread(() -> {

            lock.lock();

            try {
                for (char c : aI) {

                    System.out.println(c);
                    //先把下一个唤起，再把自己停掉
                    condition2.signal();
                    condition1.await();
                }

                condition2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }


        }, "t1").start();

        new Thread(() -> {

            lock.lock();

            try {
                for (char c : cI) {

                    System.out.println(c);
                    condition3.signal();
                    condition2.await();
                }

                condition3.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }


        }, "t2").start();

        new Thread(() -> {

            lock.lock();

            try {
                for (char c : dI) {

                    System.out.println(c);
                    condition1.signal();
                    condition3.await();
                }

                condition3.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }


        }, "t3").start();

    }


}
