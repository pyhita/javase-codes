package com.yangtao.java_version.java8.java8_in_action.ch11;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author: kante_yang
 * @Date: 2023/10/10
 */
public class CompletableFutureTest {


    @Test
    public void buildingCompletbleFuture() {
        // 已经计算好的CompletableFuture
        CompletableFuture<Integer> future1 = CompletableFuture.completedFuture(1000);

        // 返回void的CompletableFuture
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> System.out.println("run async"));

        // 返回U的CompletableFuture
        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
            return 1 + 2;
        });

    }

    // CompletableFuture 完成时的操作
    @Test
    public void complete() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(CompletableFutureTest::getMoreData);
        // 异步计算完成时
        Future<Integer> f = future.whenComplete((v, e) -> {
            System.out.println(v);
            System.out.println(e);
        });

        System.out.println(f.get());
    }

    private static int getMoreData() {
        Random rand = new Random();
        long t = System.currentTimeMillis();

        System.out.println("begin to start compute");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end to start compute. passed " + (System.currentTimeMillis() - t)/1000 + " seconds");
        int r = 1 / 0;
        return rand.nextInt(1000);
    }

    // 对比Join 和 get 对异常的处理
    @Test
    public void testJoinAndGet() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 1 / 0;
            return 100;
        });

        future.get();
//        future.join();
    }

    // 转换操作

    /**
     * 这一组函数的功能是当原来的CompletableFuture计算完后，将结果传递给函数fn，
     * 将fn的结果作为新的CompletableFuture计算结果。因此它的功能相当于
     * 将CompletableFuture<T>转换成CompletableFuture<U>。
     */
    @Test
    public void thenApply() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });

        CompletableFuture<String> f = future.thenApply(i -> i * 100).thenApply(i -> i.toString());
        System.out.println(f.join());
    }

    // 纯消费= 执行Action

    /**
     * 只对结果执行Action,而不返回新的计算值，因此计算值为Void: CompletableFuture<T> -> CompletableFuture<Void>
     */
    @Test
    public void thenAccept() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<Void> f = future.thenAccept(System.out::println);
        System.out.println(f.get());
    }

    // 组合

    /**
     * 这一组方法接受一个Function作为参数，这个Function的输入是当前的CompletableFuture的计算值，
     * 返回结果将是一个新的CompletableFuture，这个新的CompletableFuture会组合原来的CompletableFuture和函数返回的CompletableFuture。
     */
    @Test
    public void thenCompose() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });

        CompletableFuture<String> f = future.thenCompose(i -> {
            return CompletableFuture.supplyAsync(() -> {
                return (i * 10) + "";
            });
        });

        System.out.println(f.get());
    }

    // 复合操作

    /**
     * thenCombine用来复合另外一个CompletionStage的结果
     * 两个CompletionStage是并行执行的，它们之间并没有先后依赖顺序，
     * other并不会等待先前的CompletableFuture执行完毕后再执行。
     * @throws Exception
     */
    @Test
    public void thenCombine() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            return "abc";
        });

        CompletableFuture<String> f = future.thenCombine(future2, (x, y) -> x + y);
        System.out.println(f.get());
    }


    public static void main(String[] args) {

    }












}
