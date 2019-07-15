package com.java;

import java.util.concurrent.*;

/**
 * Created by hzzhaolong on 19/6/11.
 */
public class ExecutorTest {

    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newCachedThreadPool();
        Executor executor1 = Executors.newFixedThreadPool(100);
        Executor executor2 = Executors.newSingleThreadExecutor();
        Executor executor3 = Executors.newScheduledThreadPool(10);

        /**
         * 抛出异常
         * Exception in thread "main" java.util.concurrent.RejectedExecutionException: Task com.java.ExecutorTest$1@442d9b6e rejected from java.util.concurrent.ThreadPoolExecutor@ee7d9f1[Running, pool size = 1, active threads = 1, queued tasks = 1, completed tasks = 0]
                at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2047)
                at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:823)
                at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1369)
         */
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1,
//                60L, TimeUnit.SECONDS,
//                new LinkedBlockingQueue<>(1),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy());

        // 不抛出任何异常
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1,
//                60L, TimeUnit.SECONDS,
//                new LinkedBlockingQueue<>(1),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.DiscardPolicy());

        // 添加失败使用主线程来执行任务
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1,
//                60L, TimeUnit.SECONDS,
//                new LinkedBlockingQueue<>(1),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.CallerRunsPolicy());

         // 删除老的任务
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        for(int i = 0; i < 10; i++) {
            final Integer index = i;
            threadPoolExecutor.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        for(int j = 0; j < 10; j++) {
                            Thread.sleep(1000L);
                            System.out.println("index ===>" + index + " j ====>" + j);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


        CountDownLatch countDownLatch = new CountDownLatch(10);
        countDownLatch.await();
    }

}
