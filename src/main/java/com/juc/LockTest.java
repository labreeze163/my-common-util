package com.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hzzhaolong on 19/6/11.
 * ReenTrantLock独有的能力：
 *  如果你需要实现ReenTrantLock的三个独有功能时选择ReenTrantLock
 *  1. ReenTrantLock可以指定是公平锁还是非公平锁。而synchronized只能是非公平锁。所谓的公平锁就是先等待的线程先获得锁。
 *  2. ReenTrantLock提供了一个Condition（条件）类，用来实现分组唤醒需要唤醒的线程们，而不是像synchronized要么随机唤醒一个线程要么唤醒全部线程。
 *  3. ReenTrantLock提供了一种能够超时中断等待锁的线程的机制，通过lock.lockInterruptibly()来实现这个机制。
 *
 */
public class LockTest {

    public static void main(String[] args) throws InterruptedException {

        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        new LockThread(lock, condition1, condition2, true, 1).start();
        new LockThread(lock, condition1, condition2, false, 2).start();
        new LockThread(lock, condition1, condition2, true, 11).start();
        new LockThread(lock, condition1, condition2, false, 22).start();


        CountDownLatch countDownLatch = new CountDownLatch(10);
        countDownLatch.await();
    }


    public static class LockThread extends Thread{

        private Lock lock;

        private Condition condition1;

        private Condition condition2;

        private boolean isFirstType;

        private Integer threadNum;

        public LockThread(Lock lock, Condition condition1, Condition condition2, boolean isFirstType, Integer threadNum) {
            this.lock = lock;
            this.condition1 = condition1;
            this.condition2 = condition2;
            this.isFirstType = isFirstType;
            this.threadNum = threadNum;
        }

        public void run() {
            while (true) {
                try {
                    if(isFirstType) {
                        lock.lock();
                        System.out.println("第一类threadNum:" + threadNum + " ==> " + System.currentTimeMillis());
                        Thread.sleep(1000);
                        condition1.await();  // 释放锁进入等待状态
                        condition2.signalAll();
                    } else  {
                        lock.lock();
                        System.out.println("第二类threadNum:" + threadNum + " ==> " + System.currentTimeMillis());
                        Thread.sleep(1000);
                        condition1.signal(); // 先唤醒condition1
                        condition2.await(); // 注意顺序 如果先调用await 虽然会释放锁但是自己进入等待状态 唤醒方法就被block无法执行了
                    }
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
            }


        }
    }


}
