package com.yangtao.java8;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: kante_yang
 * @Date: 2023/10/19
 */
public class Test {

    @org.junit.Test
    public void test() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 1 + 2);

        // thenCompose 操作, 整合两个CF 对象，第一个CF的结果 将作为第二个CF的参数
        CompletableFuture<String> thenCompose = future
                .thenCompose(i -> CompletableFuture.supplyAsync(() -> String.valueOf(i)));

        System.out.println(thenCompose.join());
    }

    @org.junit.Test
    public void test2() {
        // thenCombine：组合两个CompletableFuture，没有先后的顺序，并行执行，最后通过BiFunction进行结果的整合
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 1 + 3);

        CompletableFuture<String> thenCombine = future.thenCombine(CompletableFuture.supplyAsync(() -> {
                    return "ddd";
                }),
                (number, str) -> {
                    return number + str;
                });

        System.out.println(thenCombine.join());
    }
}
