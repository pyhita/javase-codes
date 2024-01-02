package com.yangtao.java_version.java8.java8_test;

import java.util.concurrent.CompletableFuture;
import org.junit.Test;

/**
 * @Author: kante_yang
 * @Date: 2024/1/2
 */
public class CompletableFuture20Examples {

    // 1 创建一个完成的CompletableFuture
    @Test
    public void test1() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message");
        System.out.println(cf.isDone());
        System.out.println("message: " + cf.getNow(null));
    }

    // 2 运行一个简单的异步执行阶段
    /**
     * CompletableFuture的方法如果以Async结尾，它会异步的执行(没有指定executor的情况下)，
     * 异步执行通过ForkJoinPool实现， 它使用守护线程去执行任务。
     * 注意这是CompletableFuture的特性， 其它CompletionStage可以override这个默认的行为。
     */
    @Test
    public void runAsyncExample() {
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            System.out.println("currentThread " + Thread.currentThread().isDaemon());
        });

        cf.join();
        System.out.println(cf.isDone());
    }

    // 3 在前一个阶段应用函数
        // CompletableFuture<T> -> CompletableFuture<U>
    @Test
    public void testApplyExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message")
            .thenApply(s -> {
                return s.toUpperCase();
            });

        System.out.println(cf.join());
    }

    // 4 消费前一个阶段的结果
        // CompletableFuture<T> -> CompletableFuture<Void>
    @Test
    public void testAcceptExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture<Void> cf = CompletableFuture.completedFuture("message")
            .thenAccept(s -> result.append(s));

        cf.join();
        System.out.println(result.length());
    }


    // 5 在两个完成的阶段之一，应用函数
    @Test
    public void applyToEitherExample() {
        String original = "Message";
        CompletableFuture<String> cf1 = CompletableFuture.completedFuture(
                original)
            .thenApplyAsync(s -> delayedLowerCase(s));
        CompletableFuture<String> cf2 = cf1.applyToEither(CompletableFuture.completedFuture(
                original)
            .thenApplyAsync(s -> delayedUpperCase(s)), s -> s + " from applyToEither");
        System.out.println(cf2.join());
    }

    private static String delayedUpperCase(String original) {
        try {
            return original.toUpperCase();
        } catch (Exception e) {

        }
        return null;
    }

    private static String delayedLowerCase(String original) {
        try {
            Thread.sleep(4000);
            return original.toLowerCase();
        } catch (Exception e) {

        }
        return null;
    }

    // 6 在两个完成的阶段之一，消费结果 acceptEither

    // 7 thenCompose
    @Test
    public void thenComposeExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message")
            .thenCompose(s -> CompletableFuture.completedFuture(s.toUpperCase()));

        System.out.println(cf.join());
    }

    // 8 组合两个异步阶段的结果thenCombine
    @Test
    public void thenCombineExample() {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(
                () -> "message".toLowerCase())
            .thenCombine(CompletableFuture.supplyAsync(() -> "message".toUpperCase()),
                (s1, s2) -> s1 + "--" + s2);

        System.out.println(cf.join());
    }

    // 5 显式返回异常
    @Test
    public void completeExceptionallyExample() {

    }
}
