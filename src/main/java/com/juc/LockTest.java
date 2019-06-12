package com.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hzzhaolong on 19/6/11.
 */
public class LockTest {

    public static void main(String[] args) throws InterruptedException {

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        condition.await();
    }
}
