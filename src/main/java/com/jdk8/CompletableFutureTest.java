package com.jdk8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

/**
 * Created by hzzhaolong on 19/7/23.
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // CompletableFuture.complete() 手工的完成一个 Future
        CompletableFuture<String> completableFuture1 = new CompletableFuture<>();
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println(completableFuture1.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Thread.sleep(1000);
        completableFuture1.complete("finish");

        // 不需要返回值的可以使用runAsync()
        CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("runAsync");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                };
            }
        });
        runAsyncFuture.get();

        // 需要返回值的可以使用supplyAsync
        CompletableFuture<String> supplyAsyncFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                };
                return "supplyAsync";
            }
        });
        System.out.println(supplyAsyncFuture.get());

        // 用指定的线程池进行run
        Executor executor = Executors.newFixedThreadPool(10);
        supplyAsyncFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            };
            return "supplyAsyncWithPool";
        }, executor);
        System.out.println(supplyAsyncFuture.get());

        //  thenApply() 可以使用 thenApply() 处理和改变CompletableFuture的结果
        CompletableFuture<String> thenApplyFuture = supplyAsyncFuture.thenApply((String data) -> {
            return "thenApply-->" + data;
        });
        System.out.println(thenApplyFuture.get());

        // thenAccept() 回调方法可以持有返回对象
        supplyAsyncFuture.thenAccept((String name) -> {
            System.out.println("thenAccept -->" + name);
        });

        // thenRun()不能访Future的结果
        supplyAsyncFuture.thenRun(() -> System.out.println("thenRun  --> finish"));

        // thenApply两个 返回completableFuture<CompletableFuture<T>> result

        // thenCompose() 返回CompletableFuture<T> result
        CompletableFuture<Long> userRankingFuture = getUsersDetail("001").thenCompose((String uname) -> getCreditRating(uname));
        System.out.println("thenCompose -->" + userRankingFuture.get());

        // thenCombine() 组合两个独立的 future 同时执行完才能回调方法
        CompletableFuture<Double> heightFuture = CompletableFuture.supplyAsync(() -> {
            return 180.0;
        });

        CompletableFuture<Double> weightFuture = CompletableFuture.supplyAsync(() -> {
            return 60.0;
        });

        CompletableFuture<Double> bmiFuture = heightFuture.thenCombine(weightFuture, (height, weight) -> height / weight);
        System.out.println("thenCombine -->" + bmiFuture.get());


    }

    private static CompletableFuture<String> getUsersDetail(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            return "jack";
        });
    }

    private static CompletableFuture<Long> getCreditRating(String uname) {
        return CompletableFuture.supplyAsync(() -> {
            return 1001L;
        });
    }

}
