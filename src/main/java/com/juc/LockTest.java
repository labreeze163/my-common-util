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
 * 可重入锁的特性:
 *  1. 在线程获取锁的时候，如果已经获取锁的线程是当前线程的话则直接再次获取成功
 *  2. 由于锁会被获取n次，那么只有锁在被释放同样的n次之后，该锁才算是完全释放成功
 *
 * 公平锁和非公平锁
 *
 */
public class LockTest {

    public static void main(String[] args) throws InterruptedException {

        // 测试condition工程
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        new LockThread(lock, condition1, condition2, true, 1).start();
        new LockThread(lock, condition1, condition2, false, 2).start();
        new LockThread(lock, condition1, condition2, true, 11).start();
        new LockThread(lock, condition1, condition2, false, 22).start();


        // 测试可重入行
        Lock lock2 = new ReentrantLock();
        new RepeatableLock(1, lock2).start();
        new RepeatableLock(2, lock2).start();


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
                        condition2.signalAll();
                        condition1.await();  // 释放锁进入等待状态
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

    public static class RepeatableLock extends Thread {

        private Lock lock;

        private Integer index;

        public RepeatableLock() {

        }

        public RepeatableLock(Integer index, Lock lock) {
            this.index = index;
            this.lock = lock;
        }

        public void run() {
            try {
                lock.lock();
                System.out.println(index + "-------firstLock -----------");
                try {
                    lock.lock();
                    System.out.println(index + "--------secondLock -----------");
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                    Thread.sleep(3000);
                }
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }
    }


}
