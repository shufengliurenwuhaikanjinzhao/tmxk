package com.springcloud.tmxk.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author tanghl
 * @version v1.0.0
 * @date 2019-12-05
 * @description 异步回调机制
 **/
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 异步调用没有返回值
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName()+" 没有返回值，update mysql ok ");
        });
        voidCompletableFuture.get();

        // 异步调用有返回值
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+" \t completableFuture ");
            return 1024;
        });
        Integer integer = completableFuture.whenComplete((t, u) -> {
            System.out.println(" ==t== " + t);
            System.out.println(" ==u== " + u);
        }).exceptionally((f) -> {
            System.out.println("==Exception==" + f.getMessage());
            return 4;
        }).get();
        System.out.println(integer);

    }

}
