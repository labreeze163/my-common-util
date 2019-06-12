package com.java;

import java.util.concurrent.*;

/**
 * Created by hzzhaolong on 19/6/11.
 */
public class ExecutorTest {

    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();
        Executor executor1 = Executors.newFixedThreadPool(100);
        Executor executor2 = Executors.newSingleThreadExecutor();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        threadPoolExecutor.shutdownNow();
    }

}
